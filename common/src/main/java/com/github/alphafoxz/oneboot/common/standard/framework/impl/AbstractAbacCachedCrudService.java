package com.github.alphafoxz.oneboot.common.standard.framework.impl;

import com.github.alphafoxz.oneboot.common.CommonConstants;
import com.github.alphafoxz.oneboot.common.standard.access_control.AbacActionType;
import com.github.alphafoxz.oneboot.common.standard.access_control.AbacApi;
import com.github.alphafoxz.oneboot.common.standard.framework.*;
import com.github.alphafoxz.oneboot.common.toolkit.coding.ArrayUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.CollUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.ReflectUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.SpringUtil;
import org.jooq.Record;
import org.jooq.*;
import org.jooq.impl.TableImpl;
import org.jooq.impl.UpdatableRecordImpl;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * 带有缓存和属性访问控制的CrudService实现类
 */
public abstract class AbstractAbacCachedCrudService<TABLE extends TableImpl<RECORD>, PO extends java.lang.Record, RECORD extends UpdatableRecordImpl<RECORD>>
        implements CrudService<TABLE, PO, RECORD>, LogAble, CachePo<PO>, ReliableService, AbacService {
    @NonNull
    public abstract DSLContext getDslContext();

    @NonNull
    public abstract CacheManager getCacheManager();

    private volatile AbacApi abacApi;

    private AbacApi getAbacApi() {
        if (abacApi == null) {
            synchronized (this) {
                if (abacApi == null) {
                    abacApi = SpringUtil.getBean(AbacApi.class);
                }
            }
        }
        return abacApi;
    }

    private Field<Long> idField;

    protected Field<Long> getIdField() {
        if (idField == null) {
            idField = (Field<Long>) getTable().getPrimaryKey().getFields().get(0);
        }
        return idField;
    }

    @Override
    public void startTransaction() {
        getDslContext().startTransaction().execute();
    }

    @Override
    public void commitTransaction() {
        getDslContext().commit().execute();
    }

    @Override
    public void rollbackTransaction() {
        getDslContext().rollback().execute();
    }

    @Nullable
    @Override
    public Cache getCache() {
        return getCacheManager().getCache(getTable().getName());
    }

    public void evictCaches(@NonNull RECORD... records) {
        Cache cache = getCache();
        if (records.length == 0) {
            return;
        }
        if (cache != null) {
            for (RECORD record : records) {
                Long id = record.get(getIdField());
                if (id == null) {
                    continue;
                }
                cache.evictIfPresent(id);
            }
        }
    }

    private Long getCurrentSubjectId() {
        // TODO
        return 1704372248082780160L;
    }

    @Override
    public int insert(@NonNull RECORD record) {
        return insert(true, record);
    }

    protected int insertWithoutAc(@NonNull RECORD record) {
        return insert(false, record);
    }

    private int insert(boolean useAc, @NonNull RECORD record) {
        if (useAc && !getAbacApi().access(getCurrentSubjectId(), getTable().getSchema().getName(), getTable().getName(), null, AbacActionType.CREATE, getCreatePolicies())) {
            throw403Error(getTable().getName(), null);
        }
        return getDslContext().executeInsert(record);
    }

    @Override
    public int insertMany(@NonNull RECORD... records) {
        return insertMany(true, records);
    }

    protected int insertManyWithoutAc(@NonNull RECORD... records) {
        return insertMany(false, records);
    }

    private int insertMany(boolean useAc, @NonNull RECORD... records) {
        if (useAc && !getAbacApi().access(getCurrentSubjectId(), getTable().getSchema().getName(), getTable().getName(), null, AbacActionType.CREATE, getCreatePolicies())) {
            throw403Error(getTable().getName(), null);
        }
        int[] i = getDslContext().batchInsert(records).execute();
        return ArrayUtil.isEmpty(i) ? 0 : records.length;
    }

    @Nullable
    @Override
    public PO selectOne(@Nullable SortField<?>[] orderBy, @NonNull Condition... conditions) {
        SelectConditionStep<Record1<Long>> where = getDslContext().select(idField)
                .from(getTable())
                .where(conditions);
        org.jooq.Record record;
        if (ArrayUtil.isNotEmpty(orderBy)) {
            record = where.orderBy(orderBy).fetchAny();
        } else {
            record = where.fetchAny();
        }
        if (record == null) {
            return null;
        }
        Long id = record.getValue(getIdField());
        PO po = selectOne(id);
        return po;
    }

    @Nullable
    @Override
    public PO selectOne(long id) {
        return selectOne(true, id);
    }

    protected PO selectOneWithoutAc(long id) {
        return selectOne(false, id);
    }

    private PO selectOne(boolean useAc, long id) {
        if (useAc && !getAbacApi().access(getCurrentSubjectId(), getTable().getSchema().getName(), getTable().getName(), id, AbacActionType.READ, getReadPolicies())) {
            throw403Error(getTable().getName(), id);
        }
        PO result = queryCache(id);
        if (result != null) {
            return result;
        }
        SelectConditionStep<org.jooq.Record> where = getDslContext().select(getTable().fields())
                .from(getTable())
                .where(getIdField().eq(id));
        long l = System.currentTimeMillis();
        org.jooq.Record record = where.fetchOne();
        l = System.currentTimeMillis() - l;
        if (l > CommonConstants.SLOW_SQL_TIMEOUT_MS) {
            getLogger().warn("SQL执行时间过长({}毫秒)，请检查数据库索引等相关优化\n{}\n{}", l, where.getSQL(), where.getBindValues());
        }
        if (record == null) {
            return null;
        }
        result = record.into(getPoClass());
        putCache(id, result);
        return result;
    }

    @Override
    public PO selectOne(@NonNull Condition... conditions) {
        return selectOne(null, conditions);
    }

    @NonNull
    @Override
    public Page<PO> selectPage(int pageNum, int pageSize, @Nullable SortField<?>[] orderBy, @Nullable Condition... conditions) {
        return selectPage(true, pageNum, pageSize, orderBy, conditions);
    }

    protected Page<PO> selectPageWithoutAc(int pageNum, int pageSize, @Nullable SortField<?>[] orderBy, @Nullable Condition... conditions) {
        return selectPage(false, pageNum, pageSize, orderBy, conditions);
    }

    private Page<PO> selectPage(boolean useAc, int pageNum, int pageSize, @Nullable SortField<?>[] orderBy, @Nullable Condition... conditions) {
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize);
        int total = selectCount(useAc, conditions);
        List<PO> poList = selectList(useAc, pageRequest.getOffset(), pageSize, orderBy, conditions);
        Page<PO> poPage = new PageImpl<>(poList, pageRequest, total);
        for (PO po : poPage.getContent()) {
            Long id = po == null ? null : (long) ReflectUtil.getFieldValue(po, getIdField().getName());
            if (useAc && !getAbacApi().access(getCurrentSubjectId(), getTable().getSchema().getName(), getTable().getName(), id, AbacActionType.READ, getReadPolicies())) {
                throw403Error(getTable().getName(), id);
            }
        }
        return poPage;
    }

    @Override
    @NonNull
    public Page<PO> selectPage(int pageNum, int pageSize, @Nullable Condition... conditions) {
        return selectPage(pageNum, pageSize, null, conditions);
    }

    @NonNull
    private List<Long> selectIdList(long offset, int numOfRows, @Nullable SortField<?>[] orderBy, @Nullable Condition... conditions) {
        SelectConditionStep<Record> where = getDslContext().select(getTable().getPrimaryKey().getFields())
                .from(getTable())
                .where(conditions);
        long l = System.currentTimeMillis();
        Result<Record> fetch;
        if (ArrayUtil.isNotEmpty(orderBy)) {
            fetch = where.orderBy(orderBy).limit(offset, numOfRows).fetch();
        } else {
            fetch = where.limit(offset, numOfRows).fetch();
        }
        l = System.currentTimeMillis() - l;
        if (l > CommonConstants.SLOW_SQL_TIMEOUT_MS) {
            getLogger().warn("SQL执行时间过长({}毫秒)，请检查数据库索引等相关优化\n{}\n{}", l, where.getSQL(), where.getBindValues());
        }
        List<Long> result = CollUtil.newArrayList();
        if (fetch.isEmpty()) {
            return result;
        }
        for (Record record : fetch) {
            Long id = (Long) record.getValue(0);
            result.add(id);
        }
        return result;
    }

    @NonNull
    private List<PO> selectList(boolean useAc, long offset, int numOfRows, @Nullable SortField<?>[] orderBy, @Nullable Condition... conditions) {
        List<Long> records = selectIdList(offset, numOfRows, orderBy, conditions);
        List<PO> result = CollUtil.newArrayList();
        for (Long id : records) {
            result.add(selectOne(useAc, id));
        }
        return result;
    }

    @NonNull
    @Override
    public List<PO> selectList(int limit, @Nullable SortField<?>[] orderBy, @Nullable Condition... conditions) {
        return selectList(true, limit, orderBy, conditions);
    }

    protected List<PO> selectListWithoutAc(int limit, @Nullable SortField<?>[] orderBy, @Nullable Condition... conditions) {
        return selectList(false, limit, orderBy, conditions);
    }

    private List<PO> selectList(boolean useAc, int limit, @Nullable SortField<?>[] orderBy, @Nullable Condition... conditions) {
        return selectList(useAc, 0, limit, orderBy, conditions);
    }

    @Override
    @NonNull
    public List<PO> selectList(int limit, @Nullable Condition... conditions) {
        return selectList(limit, null, conditions);
    }

    @NonNull
    protected List<PO> selectListWithoutAc(int limit, @Nullable Condition... conditions) {
        return selectListWithoutAc(limit, null, conditions);
    }

    @Override
    public int selectCount(@Nullable Condition... conditions) {
        return selectCount(true, conditions);
    }

    protected int selectCountWithoutAc(@Nullable Condition... conditions) {
        return selectCount(false, conditions);
    }

    private int selectCount(boolean useAc, @Nullable Condition... conditions) {
        if (useAc && !getAbacApi().access(getCurrentSubjectId(), getTable().getSchema().getName(), getTable().getName(), null, AbacActionType.READ, getReadPolicies())) {
            throw403Error(getTable().getName(), null);
        }
        if (ArrayUtil.isEmpty(conditions)) {
            return getDslContext().fetchCount(getTable());
        }
        return getDslContext().fetchCount(getTable(), conditions);
    }

    @Override
    public int update(@NonNull RECORD record) {
        return update(true, record);
    }

    protected int updateWithoutAc(@NonNull RECORD record) {
        return update(false, record);
    }

    private int update(boolean useAc, @NonNull RECORD record) {
        Long id = record.get(getIdField());
        if (useAc && !getAbacApi().access(getCurrentSubjectId(), getTable().getSchema().getName(), getTable().getName(), id, AbacActionType.UPDATE, getUpdatePolicies())) {
            throw403Error(getTable().getName(), id);
        }
        if (id == null) {
            return 0;
        }
        evictCache(id);
        int i = getDslContext().executeUpdate(record);
        evictCache(id);
        return i;
    }

    @Override
    public int deleteById(@NonNull RECORD record) {
        Long id = record.get(getIdField());
        if (id == null) {
            return 0;
        }
        int i = deleteById(id);
        return i;
    }

    @Override
    public int deleteById(long id) {
        return deleteById(true, id);
    }

    protected int deleteByIdWithoutAc(long id) {
        return deleteById(false, id);
    }

    private int deleteById(boolean useAc, long id) {
        if (useAc && !getAbacApi().access(getCurrentSubjectId(), getTable().getSchema().getName(), getTable().getName(), id, AbacActionType.DELETE, getDeletePolicies())) {
            throw403Error(getTable().getName(), id);
        }
        evictCache(id);
        int i = getDslContext().delete(getTable())
                .where(getIdField().getName() + " = ?", id)
                .execute();
        evictCache(id);
        return i;
    }

    @Override
    public int[] deleteByIds(@NonNull RECORD... records) {
        return deleteByIds(true, records);
    }

    protected int[] deleteByIdsWithoutAc(@NonNull RECORD... records) {
        return deleteByIds(false, records);
    }

    private int[] deleteByIds(boolean useAc, @NonNull RECORD... records) {
        if (useAc) {
            for (RECORD record : records) {
                Long id = record.get(getIdField());
                if (!getAbacApi().access(getCurrentSubjectId(), getTable().getSchema().getName(), getTable().getName(), id, AbacActionType.DELETE, getDeletePolicies())) {
                    throw403Error(getTable().getName(), id);
                }
            }
        }
        evictCaches(records);
        int[] execute = getDslContext().batchDelete(records).execute();
        evictCaches(records);
        return execute;
    }

    @Override
    public int deleteByIds(@NonNull long... ids) {
        return deleteByIds(true, ids);
    }

    protected int deleteByIdsWithoutAc(@NonNull long... ids) {
        return deleteByIds(false, ids);
    }

    private int deleteByIds(boolean useAc, @NonNull long... ids) {
        if (useAc) {
            for (long id : ids) {
                if (!getAbacApi().access(getCurrentSubjectId(), getTable().getSchema().getName(), getTable().getName(), id, AbacActionType.DELETE, getDeletePolicies())) {
                    throw403Error(getTable().getName(), id);
                }
            }
        }
        if (ArrayUtil.isEmpty(ids)) {
            return 0;
        }
        evictCache(ids);
        int i = getDslContext().delete(getTable())
                .where(getIdField().in(CollUtil.newArrayList(ids)))
                .execute();
        evictCache(ids);
        return i;
    }
}
