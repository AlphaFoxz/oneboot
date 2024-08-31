package com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo;


import jakarta.annotation.Nonnull;

/**
 * 访问控制属性
 */
public record AbacAttrVo(
        @Nonnull
        String name,
        @Nonnull
        String value) {
}