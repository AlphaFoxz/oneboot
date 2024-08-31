package com.github.alphafoxz.oneboot.core.standard.service;

import org.springframework.lang.Nullable;

/**
 * 可缓存的
 */
public interface Cacheable<T> {
    @Nullable
    public T getCache();
}
