package com.github.alphafoxz.oneboot.sdk.gen.restful.enums;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Schema(description = "CRUD service的枚举类")
@AllArgsConstructor
@Getter
public enum SdkCrudServiceTypeEnum {
    /**有缓存的增删改查*/
    CACHED(0),
    /**ABAC数据鉴权 + 缓存的增删改查*/
    ABAC_CACHED(1);

    private final int value;
}