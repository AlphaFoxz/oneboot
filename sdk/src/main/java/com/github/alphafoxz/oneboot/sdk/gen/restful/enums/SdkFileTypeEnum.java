package com.github.alphafoxz.oneboot.sdk.gen.restful.enums;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Schema(description = "文件类型")
@AllArgsConstructor
@Getter
public enum SdkFileTypeEnum {
    /**本地文件*/
    LOCAL_FILE(0),
    /**本地文件夹*/
    LOCAL_DIR(1);

    private final int value;
}