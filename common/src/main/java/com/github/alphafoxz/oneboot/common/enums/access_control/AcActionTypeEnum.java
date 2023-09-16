package com.github.alphafoxz.oneboot.common.enums.access_control;

import com.github.alphafoxz.oneboot.common.ifaces.access_control.AcActionTypeIface;
import lombok.Getter;

@Getter
public enum AcActionTypeEnum implements AcActionTypeIface {
    READ("R"),
    CREATE("C"),
    EDIT("U"),
    LOGIC_DELETE("LD"),
    REMOVE("D");

    private final String name;

    private AcActionTypeEnum(String name) {
        this.name = name;
    }
}
