package com.github.alphafoxz.oneboot.sdk.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(name = "SdkVersionCheckDto", description = "版本检查结果")
@Getter
public class SdkVersionCheckDto {
    @Schema(name = "filePath", description = "")
    private String filePath;
    @Schema(name = "sha256", description = "")
    private String sha256;
    @Schema(name = "same", description = "")
    private Boolean same;
    @Schema(name = "message", description = "")
    private String message;

    public SdkVersionCheckDto setFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }
    public SdkVersionCheckDto setSha256(String sha256) {
        this.sha256 = sha256;
        return this;
    }
    public SdkVersionCheckDto setSame(Boolean same) {
        this.same = same;
        return this;
    }
    public SdkVersionCheckDto setMessage(String message) {
        this.message = message;
        return this;
    }
}