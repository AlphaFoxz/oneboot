package com.github.alphafoxz.oneboot.core.toolkit.coding;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapUtil extends cn.hutool.core.map.MapUtil {
    public static <K, V> Map<K, V> newLinkedHashMap() {
        return new LinkedHashMap<>();
    }
}
