package com.github.alphafoxz.oneboot.core.standard.framework;

import org.springframework.lang.Nullable;

/**
 * 可缓存的
 */
public interface Cacheable<T> {
    @Nullable
    public T getCache();
}
