package com.github.alphafoxz.oneboot.sdk.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import com.github.alphafoxz.oneboot.sdk.gen.restful.dtos.SdkThriftTemplateDto;

@Schema(name = "SdkThriftTemplateResponseDto", description = "")
@Getter
public class SdkThriftTemplateResponseDto {
    @Schema(name = "id", description = "")
    private Long id;
    @Schema(name = "taskId", description = "")
    private Long taskId;
    @Schema(name = "success", description = "")
    private Boolean success;
    @Schema(name = "message", description = "")
    private String message;
    @Schema(name = "data", description = "")
    private SdkThriftTemplateDto data;

    public SdkThriftTemplateResponseDto setId(Long id) {
        this.id = id;
        return this;
    }
    public SdkThriftTemplateResponseDto setTaskId(Long taskId) {
        this.taskId = taskId;
        return this;
    }
    public SdkThriftTemplateResponseDto setSuccess(Boolean success) {
        this.success = success;
        return this;
    }
    public SdkThriftTemplateResponseDto setMessage(String message) {
        this.message = message;
        return this;
    }
    public SdkThriftTemplateResponseDto setData(SdkThriftTemplateDto data) {
        this.data = data;
        return this;
    }
}