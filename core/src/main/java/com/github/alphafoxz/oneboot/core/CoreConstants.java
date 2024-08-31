package com.github.alphafoxz.oneboot.core;

/**
 * core模块常量
 */
public final class CoreConstants {
    /**
     * service缓存key生成器的bean名称
     */
    @Deprecated
    public static final String SERVICE_CACHE_KEY_GENERATOR = "serviceCacheKeyGenerator";
    /**
     * 空的json数组
     */
    public static final String EMPTY_JSON_ARR = "[]";
    /**
     * 空的json对象
     */
    public static final String EMPTY_JSON_OBJ = "{}";
    /**
     * 慢SQL判定阈值毫秒
     */
    public static final long SLOW_SQL_TIMEOUT_MS = 10;
}
