package com.github.alphafoxz.oneboot.preset_sys.service.framework;

import com.github.alphafoxz.oneboot.common.standard.framework.CustomCacheService;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.io.Serializable;

public class CustomCacheServiceImpl implements CustomCacheService {
    @Resource
    private CacheManager cacheManager;
    private volatile Cache cache;

    @Override
    @Nonnull
    public Cache getCache() {
        if (cache == null) {
            synchronized (this) {
                if (cache == null) {
                    cache = cacheManager.getCache(DEFAULT_MODULE_NAME);
                }
            }
        }
        return cache;
    }

    public Cache getCache(String moduleName) {
        return cacheManager.getCache("_" + moduleName + "_");
    }

    @Override
    public void put(String key, Serializable value) {
        getCache().put(key, value);
    }

    @Override
    public void put(String moduleName, String key, Serializable value) {
        getCache(moduleName).put(key, value);
    }

    @Override
    public Serializable get(String key) {
        return (Serializable) getCache().get(key);
    }

    @Override
    public Serializable get(String moduleName, String key) {
        return (Serializable) getCache(moduleName).get(key);
    }
}
