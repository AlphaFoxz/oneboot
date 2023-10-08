package com.github.alphafoxz.oneboot.sdk.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import java.util.List;

@Schema(name = "SdkListResponseDto", description = "")
@Getter
public class SdkListResponseDto {
    @Schema(name = "id", description = "")
    private Long id;
    @Schema(name = "taskId", description = "")
    private Long taskId;
    @Schema(name = "success", description = "")
    private Boolean success;
    @Schema(name = "message", description = "")
    private String message;
    @Schema(name = "data", description = "")
    private List<String> data;

    public SdkListResponseDto setId(Long id) {
        this.id = id;
        return this;
    }
    public SdkListResponseDto setTaskId(Long taskId) {
        this.taskId = taskId;
        return this;
    }
    public SdkListResponseDto setSuccess(Boolean success) {
        this.success = success;
        return this;
    }
    public SdkListResponseDto setMessage(String message) {
        this.message = message;
        return this;
    }
    public SdkListResponseDto setData(List<String> data) {
        this.data = data;
        return this;
    }
}