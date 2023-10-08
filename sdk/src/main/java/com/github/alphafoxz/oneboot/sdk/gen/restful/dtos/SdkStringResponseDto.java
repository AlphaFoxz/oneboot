package com.github.alphafoxz.oneboot.sdk.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(name = "SdkStringResponseDto", description = "")
@Getter
public class SdkStringResponseDto {
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

    public SdkStringResponseDto setId(Long id) {
        this.id = id;
        return this;
    }
    public SdkStringResponseDto setTaskId(Long taskId) {
        this.taskId = taskId;
        return this;
    }
    public SdkStringResponseDto setSuccess(Boolean success) {
        this.success = success;
        return this;
    }
    public SdkStringResponseDto setMessage(String message) {
        this.message = message;
        return this;
    }
    public SdkStringResponseDto setData(String data) {
        this.data = data;
        return this;
    }
}