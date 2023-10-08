package com.github.alphafoxz.oneboot.sdk.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import java.util.Map;

@Schema(name = "SdkMapResponseDto", description = "")
@Getter
public class SdkMapResponseDto {
    @Schema(name = "id", description = "")
    private Long id;
    @Schema(name = "taskId", description = "")
    private Long taskId;
    @Schema(name = "success", description = "")
    private Boolean success;
    @Schema(name = "message", description = "")
    private String message;
    @Schema(name = "data", description = "")
    private Map<String, String> data;

    public SdkMapResponseDto setId(Long id) {
        this.id = id;
        return this;
    }
    public SdkMapResponseDto setTaskId(Long taskId) {
        this.taskId = taskId;
        return this;
    }
    public SdkMapResponseDto setSuccess(Boolean success) {
        this.success = success;
        return this;
    }
    public SdkMapResponseDto setMessage(String message) {
        this.message = message;
        return this;
    }
    public SdkMapResponseDto setData(Map<String, String> data) {
        this.data = data;
        return this;
    }
}