package com.github.alphafoxz.oneboot.core.standard.service;

import org.springframework.cache.Cache;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * 缓存主键的service
 *
 * @param <PO> Jooq po实体类
 */
public interface CachePo<PO extends Record> extends Cacheable<Cache> {
    @Nullable
    @SuppressWarnings("unchecked")
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

    public default void evictCache(@NonNull long... ids) {
        Cache cache = getCache();
        if (cache != null) {
            for (long id : ids) {
                cache.evictIfPresent(id);
            }
        }
    }

    public default void clearCache() {
        Cache cache = getCache();
        if (cache != null) {
            cache.clear();
        }
    }
}
