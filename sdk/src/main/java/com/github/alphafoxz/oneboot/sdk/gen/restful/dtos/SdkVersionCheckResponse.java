package com.github.alphafoxz.oneboot.sdk.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import java.util.List;

@Schema(name = "SdkVersionCheckResponse", description = "版本检查响应")
@Getter
public class SdkVersionCheckResponse {
    @Schema(name = "id", description = "")
    private Long id;
    @Schema(name = "taskId", description = "")
    private Long taskId;
    @Schema(name = "success", description = "")
    private Boolean success;
    @Schema(name = "message", description = "")
    private String message;
    @Schema(name = "data", description = "")
    private List<SdkVersionCheckDto> data;

    public SdkVersionCheckResponse setId(Long id) {
        this.id = id;
        return this;
    }
    public SdkVersionCheckResponse setTaskId(Long taskId) {
        this.taskId = taskId;
        return this;
    }
    public SdkVersionCheckResponse setSuccess(Boolean success) {
        this.success = success;
        return this;
    }
    public SdkVersionCheckResponse setMessage(String message) {
        this.message = message;
        return this;
    }
    public SdkVersionCheckResponse setData(List<SdkVersionCheckDto> data) {
        this.data = data;
        return this;
    }
}