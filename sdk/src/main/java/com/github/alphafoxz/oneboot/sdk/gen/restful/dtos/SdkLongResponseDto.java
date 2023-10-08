package com.github.alphafoxz.oneboot.sdk.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(name = "SdkLongResponseDto", description = "")
@Getter
public class SdkLongResponseDto {
    @Schema(name = "id", description = "")
    private Long id;
    @Schema(name = "taskId", description = "")
    private Long taskId;
    @Schema(name = "success", description = "")
    private Boolean success;
    @Schema(name = "message", description = "")
    private String message;
    @Schema(name = "data", description = "")
    private Long data;

    public SdkLongResponseDto setId(Long id) {
        this.id = id;
        return this;
    }
    public SdkLongResponseDto setTaskId(Long taskId) {
        this.taskId = taskId;
        return this;
    }
    public SdkLongResponseDto setSuccess(Boolean success) {
        this.success = success;
        return this;
    }
    public SdkLongResponseDto setMessage(String message) {
        this.message = message;
        return this;
    }
    public SdkLongResponseDto setData(Long data) {
        this.data = data;
        return this;
    }
}