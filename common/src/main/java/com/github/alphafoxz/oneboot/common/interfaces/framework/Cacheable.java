package com.github.alphafoxz.oneboot.common.interfaces.framework;

import org.springframework.lang.Nullable;

/**
 * 可缓存的
 */
public interface Cacheable<T> {
    @Nullable
    public T getCache();
}
