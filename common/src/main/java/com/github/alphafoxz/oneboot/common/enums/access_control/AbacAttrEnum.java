package com.github.alphafoxz.oneboot.common.enums.access_control;

import com.github.alphafoxz.oneboot.common.ifaces.access_control.AbacAttrIface;
import lombok.Getter;

@Getter
public enum AbacAttrEnum implements AbacAttrIface {
    /**
     * 安全管理员角色
     */
    SECURITY_ADMIN("SECURITY_ADMIN", null);

    private final String name;
    private final String value;

    AbacAttrEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
