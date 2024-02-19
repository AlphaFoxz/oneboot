package com.github.alphafoxz.oneboot.preset_sys.gen.restl.enums;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Schema(description = "Abac动态授权类型")
@AllArgsConstructor
@Getter
public enum PsysAbacDynamicAuthTypeEnum {
    /**主动*/
    INITIATIVE(0),
    /**被动*/
    PASSIVE(1);

    private final int value;
}