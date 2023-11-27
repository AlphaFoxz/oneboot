package com.github.alphafoxz.oneboot.sdk.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(name = "SdkStringRequestDto", description = "")
@Getter
public class SdkStringRequestDto {
    @Schema(name = "id", description = "")
    private Long id;
    @Schema(name = "taskId", description = "")
    private Long taskId;
    @Schema(name = "data", description = "")
    private String data;

    public SdkStringRequestDto setId(Long id) {
        this.id = id;
        return this;
    }
    public SdkStringRequestDto setTaskId(Long taskId) {
        this.taskId = taskId;
        return this;
    }
    public SdkStringRequestDto setData(String data) {
        this.data = data;
        return this;
    }
}