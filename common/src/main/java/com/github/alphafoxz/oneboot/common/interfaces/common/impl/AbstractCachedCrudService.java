package com.github.alphafoxz.oneboot.common.interfaces.common.impl;

import com.github.alphafoxz.oneboot.common.CommonConstants;
import com.github.alphafoxz.oneboot.common.interfaces.common.CachePo;
import com.github.alphafoxz.oneboot.common.interfaces.common.CrudService;
import com.github.alphafoxz.oneboot.common.interfaces.common.LogAble;
import com.github.alphafoxz.oneboot.common.interfaces.common.ReliableService;
import com.github.alphafoxz.oneboot.common.toolkit.coding.ArrayUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.CollUtil;
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
 * 带有缓存功能的CrudService实现类
 */
public abstract class AbstractCachedCrudService<TABLE extends TableImpl<RECORD>, PO extends java.lang.Record, RECORD extends UpdatableRecordImpl<RECORD>>
        implements CrudService<TABLE, PO, RECORD>, LogAble, CachePo<PO>, ReliableService {
    @NonNull
    public abstract DSLContext getDslContext();

    @NonNull
    public abstract CacheManager getCacheManager();

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

    @Override
    public int insert(@NonNull RECORD record) {
        return getDslContext().executeInsert(record);
    }

    @Override
    public int insertMany(@NonNull RECORD... records) {
        int[] i = getDslContext().batchInsert(records).execute();
        return ArrayUtil.isEmpty(i) ? 0 : records.length;
    }

    @Nullable
    @Override
    public PO selectOne(@Nullable SortField<?>[] orderBy, @NonNull Condition... conditions) {
        SelectConditionStep<Record1<Long>> where = getDslContext().select(idField)
                .from(getTable())
                .where(conditions);
        Record record;
        if (ArrayUtil.isNotEmpty(orderBy)) {
            record = where.orderBy(orderBy).fetchAny();
        } else {
            record = where.fetchAny();
        }
        if (record == null) {
            return null;
        }
        Long id = record.getValue(getIdField());
        return selectOne(id);
    }

    @Nullable
    @Override
    public PO selectOne(long id) {
        PO result = queryCache(id);
        if (result != null) {
            return result;
        }
        SelectConditionStep<Record> where = getDslContext().select(getTable().fields())
                .from(getTable())
                .where(getIdField().eq(id));
        long l = System.currentTimeMillis();
        Record record = where.fetchOne();
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

    @NonNull
    @Override
    public Page<PO> selectPage(int pageNum, int pageSize, @Nullable SortField<?>[] orderBy, @Nullable Condition... conditions) {
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize);
        int total = selectCount(conditions);
        List<PO> poList = selectList(pageRequest.getOffset(), pageSize, orderBy, conditions);
        return new PageImpl<>(poList, pageRequest, total);
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
    private List<PO> selectList(long offset, int numOfRows, @Nullable SortField<?>[] orderBy, @Nullable Condition... conditions) {
        List<Long> records = selectIdList(offset, numOfRows, orderBy, conditions);
        List<PO> result = CollUtil.newArrayList();
        for (Long id : records) {
            result.add(selectOne(id));
        }
        return result;
    }

    @NonNull
    @Override
    public List<PO> selectList(int limit, @Nullable SortField<?>[] orderBy, @Nullable Condition... conditions) {
        return selectList(0, limit, orderBy, conditions);
    }

    @Override
    public int selectCount(@Nullable Condition... conditions) {
        if (ArrayUtil.isEmpty(conditions)) {
            return getDslContext().fetchCount(getTable());
        }
        return getDslContext().fetchCount(getTable(), conditions);
    }

    @Override
    public int update(@NonNull RECORD record) {
        Long id = record.get(getIdField());
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
        evictCache(id);
        int i = getDslContext().delete(getTable())
                .where(getIdField().getName() + " = ?", id)
                .execute();
        evictCache(id);
        return i;
    }

    @Override
    public int[] deleteByIds(@NonNull RECORD... records) {
        evictCaches(records);
        int[] execute = getDslContext().batchDelete(records).execute();
        evictCaches(records);
        return execute;
    }

    @Override
    public int deleteByIds(@NonNull long... ids) {
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
