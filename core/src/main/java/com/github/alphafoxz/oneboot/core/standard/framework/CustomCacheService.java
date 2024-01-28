package com.github.alphafoxz.oneboot.core.standard.framework;

import org.springframework.cache.Cache;

import java.io.Serializable;

public interface CustomCacheService extends Cacheable<Cache> {
    public static final String DEFAULT_MODULE_NAME = "_CUSTOM_CACHE_";

    public void put(String key, Serializable value);

    public void put(String moduleName, String key, Serializable value);

    public Serializable get(String key);

    public Serializable get(String moduleName, String key);
}
