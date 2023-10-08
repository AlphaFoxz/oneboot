package com.github.alphafoxz.oneboot.sdk.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import com.github.alphafoxz.oneboot.sdk.gen.restful.dtos.SdkFileInfoDto;

@Schema(name = "SdkFileTreeResponseDto", description = "")
@Getter
public class SdkFileTreeResponseDto {
    @Schema(name = "id", description = "")
    private Long id;
    @Schema(name = "taskId", description = "")
    private Long taskId;
    @Schema(name = "success", description = "")
    private Boolean success;
    @Schema(name = "message", description = "")
    private String message;
    @Schema(name = "data", description = "")
    private SdkFileInfoDto data;

    public SdkFileTreeResponseDto setId(Long id) {
        this.id = id;
        return this;
    }
    public SdkFileTreeResponseDto setTaskId(Long taskId) {
        this.taskId = taskId;
        return this;
    }
    public SdkFileTreeResponseDto setSuccess(Boolean success) {
        this.success = success;
        return this;
    }
    public SdkFileTreeResponseDto setMessage(String message) {
        this.message = message;
        return this;
    }
    public SdkFileTreeResponseDto setData(SdkFileInfoDto data) {
        this.data = data;
        return this;
    }
}