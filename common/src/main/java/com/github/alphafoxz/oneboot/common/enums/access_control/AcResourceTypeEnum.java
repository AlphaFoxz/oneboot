package com.github.alphafoxz.oneboot.common.enums.access_control;

import com.github.alphafoxz.oneboot.common.ifaces.access_control.AcResourceTypeIface;
import lombok.Getter;

/**
 * 访问控制资源类型
 */
@Getter
public enum AcResourceTypeEnum implements AcResourceTypeIface {
    /**
     * 表
     */
    TABLE("0"),
    /**
     * 记录
     */
    RECORD("1");

    private final String name;

    private AcResourceTypeEnum(String name) {
        this.name = name;
    }
}
