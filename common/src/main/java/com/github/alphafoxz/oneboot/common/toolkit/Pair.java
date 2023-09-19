package com.github.alphafoxz.oneboot.common.toolkit;

public class Pair<K, V> extends cn.hutool.core.lang.Pair<K, V> {
    /**
     * 构造
     *
     * @param key   键
     * @param value 值
     */
    public Pair(K key, V value) {
        super(key, value);
    }

    /**
     * 构建{@code Pair}对象
     *
     * @param <K>   键类型
     * @param <V>   值类型
     * @param key   键
     * @param value 值
     * @return {@code Pair}
     * @since 5.4.3
     */
    public static <K, V> Pair<K, V> of(K key, V value) {
        return new Pair<>(key, value);
    }
}
