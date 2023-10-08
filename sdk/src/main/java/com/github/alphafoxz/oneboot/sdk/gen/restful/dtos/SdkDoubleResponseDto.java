package com.github.alphafoxz.oneboot.sdk.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(name = "SdkDoubleResponseDto", description = "")
@Getter
public class SdkDoubleResponseDto {
    @Schema(name = "id", description = "")
    private Long id;
    @Schema(name = "taskId", description = "")
    private Long taskId;
    @Schema(name = "success", description = "")
    private Boolean success;
    @Schema(name = "message", description = "")
    private String message;
    @Schema(name = "data", description = "")
    private Double data;

    public SdkDoubleResponseDto setId(Long id) {
        this.id = id;
        return this;
    }
    public SdkDoubleResponseDto setTaskId(Long taskId) {
        this.taskId = taskId;
        return this;
    }
    public SdkDoubleResponseDto setSuccess(Boolean success) {
        this.success = success;
        return this;
    }
    public SdkDoubleResponseDto setMessage(String message) {
        this.message = message;
        return this;
    }
    public SdkDoubleResponseDto setData(Double data) {
        this.data = data;
        return this;
    }
}