package com.github.alphafoxz.oneboot.sdk.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(name = "SdkBinaryResponseDto", description = "")
@Getter
public class SdkBinaryResponseDto {
    @Schema(name = "id", description = "")
    private Long id;
    @Schema(name = "taskId", description = "")
    private Long taskId;
    @Schema(name = "success", description = "")
    private Boolean success;
    @Schema(name = "message", description = "")
    private String message;
    @Schema(name = "data", description = "")
    private String data;

    public SdkBinaryResponseDto setId(Long id) {
        this.id = id;
        return this;
    }
    public SdkBinaryResponseDto setTaskId(Long taskId) {
        this.taskId = taskId;
        return this;
    }
    public SdkBinaryResponseDto setSuccess(Boolean success) {
        this.success = success;
        return this;
    }
    public SdkBinaryResponseDto setMessage(String message) {
        this.message = message;
        return this;
    }
    public SdkBinaryResponseDto setData(String data) {
        this.data = data;
        return this;
    }
}