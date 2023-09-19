package com.github.alphafoxz.oneboot.common.interfaces.common;

import org.springframework.cache.Cache;
import org.springframework.lang.Nullable;

/**
 *
 */
public interface Cacheable {
    @Nullable
    public Cache getCache();
}
