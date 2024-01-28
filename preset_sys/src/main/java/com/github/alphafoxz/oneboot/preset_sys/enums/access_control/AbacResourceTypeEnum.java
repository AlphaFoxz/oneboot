package com.github.alphafoxz.oneboot.preset_sys.enums.access_control;

import com.github.alphafoxz.oneboot.core.standard.access_control.AbacResourceType;
import lombok.Getter;

/**
 * 访问控制资源类型
 */
@Getter
public enum AbacResourceTypeEnum implements AbacResourceType {
    /**
     * 表
     */
    TABLE("0"),
    /**
     * 记录
     */
    RECORD("1");

    private final String name;

    private AbacResourceTypeEnum(String name) {
        this.name = name;
    }
}
