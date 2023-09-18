package com.github.alphafoxz.oneboot.common.ifaces;

import com.github.alphafoxz.oneboot.common.toolkit.coding.ArrayUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.CollUtil;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.impl.TableImpl;
import org.jooq.impl.UpdatableRecordImpl;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public interface CachedCrudService<TABLE extends TableImpl<RECORD>, PO extends Serializable, RECORD extends UpdatableRecordImpl<RECORD>> {
    @NonNull
    public TABLE getTable();

    @Nullable
    public default PO queryCache(long id) {
        Cache cache = getCache();
        PO result = null;
        if (cache != null) {
            Cache.ValueWrapper valueWrapper = cache.get(id);
            if (valueWrapper != null) {
                result = (PO) valueWrapper.get();
            }
        }
        return result;
    }

    public default void putCache(long id, PO record) {
        Cache cache = getCache();
        if (cache != null) {
            cache.put(id, record);
        }
    }

    public default void evictCache(@NonNull RECORD... records) {
        Cache cache = getCache();
        if (cache != null) {
            for (RECORD record : records) {
                Long id = (Long) record.get("id");
                if (id == null) {
                    continue;
                }
                cache.evictIfPresent(id);
            }
        }
    }

    public default void evictCache(@NonNull long... ids) {
        Cache cache = getCache();
        if (cache != null) {
            for (long id : ids) {
                cache.evictIfPresent(id);
            }
        }
    }

    @Nullable
    public default Cache getCache() {
        return getCacheManager().getCache(getTable().getName());
    }

    @NonNull
    public CacheManager getCacheManager();

    @NonNull
    public Class<PO> getPoClass();

    @NonNull
    public DSLContext getDslContext();

    public default int insert(@NonNull RECORD record) {
        return getDslContext().executeInsert(record);
    }

    public default int insertMany(@NonNull RECORD... records) {
        int[] i = getDslContext().batchInsert(records).execute();
        return ArrayUtil.isEmpty(i) ? 0 : records.length;
    }

    @Nullable
    public default PO queryOne(long id) {
        PO result = queryCache(id);
        if (result != null) {
            return result;
        }
        Record record = getDslContext().select(getTable().fields())
                .from(getTable())
                .where(getTable().getPrimaryKey().getFields().get(0).getName() + " = ?", id)
                .fetchOne();
        if (record == null) {
            return null;
        }
        result = record.into(getPoClass());
        putCache(id, result);
        return result;
    }

    @NonNull
    public default Page<PO> queryPage(int pageNum, int pageSize, Condition... conditions) {
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize);
        int total = queryCount(conditions);
        List<PO> poList = queryList(pageRequest.getOffset(), pageSize, conditions);
        return new PageImpl<>(poList, pageRequest, total);
    }

    @NonNull
    @CacheEvict
    public default List<Long> queryIdList(long offset, int numOfRows, Condition... conditions) {
        Result<Record> fetch = getDslContext().select(getTable().getPrimaryKey().getFields())
                .from(getTable())
                .where(conditions)
                .limit(offset, numOfRows)
                .fetch();
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
    public default List<PO> queryList(int limit, Condition... conditions) {
        return queryList(0, limit, conditions);
    }

    @NonNull
    public default List<PO> queryList(long offset, int numOfRows, Condition... conditions) {
        List<Long> records = queryIdList(offset, numOfRows, conditions);
        List<PO> result = CollUtil.newArrayList();
        for (Long id : records) {
            result.add(queryOne(id));
        }
        return result;
    }

    public default int queryCount(Condition... conditions) {
        return getDslContext().fetchCount(getTable(), conditions);
    }

    public default int update(@NonNull RECORD record) {
        Long id = (Long) record.get("id");
        if (id == null) {
            return 0;
        }
        evictCache(id);
        int i = getDslContext().executeUpdate(record);
        evictCache(id);
        return i;
    }

    public default int deleteById(@NonNull RECORD record) {
        Long id = (Long) record.get("id");
        if (id == null) {
            return 0;
        }
        evictCache(id);
        int i = getDslContext().executeDelete(record);
        evictCache(id);
        return i;
    }

    public default int deleteById(long id) {
        evictCache(id);
        int i = getDslContext().delete(getTable())
                .where(getTable().getPrimaryKey().getFields().get(0).getName() + " = ?", id)
                .execute();
        evictCache(id);
        return i;
    }

    public default int[] deleteByIds(@NonNull RECORD... records) {
        evictCache(records);
        int[] execute = getDslContext().batchDelete(records).execute();
        evictCache(records);
        return execute;
    }

    public default int deleteByIds(@NonNull long... ids) {
        evictCache(ids);
        int i = getDslContext().delete(getTable())
                .where(getTable().getPrimaryKey().getFields().get(0).in(Arrays.asList(ids)))
                .execute();
        evictCache(ids);
        return i;
    }

    public default void clearCache() {
        Cache cache = getCache();
        if (cache != null) {
            cache.clear();
        }
    }
}
