package com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo;


import com.github.alphafoxz.oneboot.core.domain.DomainArgCheckException;
import com.github.alphafoxz.oneboot.core.toolkit.coding.StrUtil;
import jakarta.annotation.Nonnull;

/**
 * 访问控制属性
 */
public record AbacAttrVo(
        @Nonnull
        String name,
        @Nonnull
        String value) {
    public AbacAttrVo {
        if (StrUtil.isBlank(name) || StrUtil.isBlank(value)) {
            throw new DomainArgCheckException("abac属性名和属性值不能为空");
        }
    }
}