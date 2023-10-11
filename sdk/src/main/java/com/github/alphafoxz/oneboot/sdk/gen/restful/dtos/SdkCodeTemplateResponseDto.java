package com.github.alphafoxz.oneboot.sdk.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(name = "SdkCodeTemplateResponseDto", description = "")
@Getter
public class SdkCodeTemplateResponseDto {
    @Schema(name = "id", description = "")
    private Long id;
    @Schema(name = "taskId", description = "")
    private Long taskId;
    @Schema(name = "success", description = "")
    private Boolean success;
    @Schema(name = "message", description = "")
    private String message;
    @Schema(name = "data", description = "")
    private SdkCodeTemplateDto data;

    public SdkCodeTemplateResponseDto setId(Long id) {
        this.id = id;
        return this;
    }
    public SdkCodeTemplateResponseDto setTaskId(Long taskId) {
        this.taskId = taskId;
        return this;
    }
    public SdkCodeTemplateResponseDto setSuccess(Boolean success) {
        this.success = success;
        return this;
    }
    public SdkCodeTemplateResponseDto setMessage(String message) {
        this.message = message;
        return this;
    }
    public SdkCodeTemplateResponseDto setData(SdkCodeTemplateDto data) {
        this.data = data;
        return this;
    }
}