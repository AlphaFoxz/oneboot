package com.github.alphafoxz.oneboot.core.domain;

import cn.hutool.core.util.EnumUtil;

import java.io.Serializable;

public interface DomainEnum<T extends DomainEnum<T>> {
    default public Serializable intoDbVal() {
        return EnumUtil.toString((Enum<?>) this);
    }
}
