package com.github.alphafoxz.oneboot.common.interfaces.access_control;

import org.springframework.lang.NonNull;

/**
 * 属性访问控制-属性名
 */
public interface AbacAttrName {
    @NonNull
    public String getName();
}
