package com.github.alphafoxz.oneboot.sdk.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(name = "SdkLongRequestDto", description = "")
@Getter
public class SdkLongRequestDto {
    @Schema(name = "id", description = "")
    private Long id;
    @Schema(name = "taskId", description = "")
    private Long taskId;
    @Schema(name = "data", description = "")
    private Long data;

    public SdkLongRequestDto setId(Long id) {
        this.id = id;
        return this;
    }
    public SdkLongRequestDto setTaskId(Long taskId) {
        this.taskId = taskId;
        return this;
    }
    public SdkLongRequestDto setData(Long data) {
        this.data = data;
        return this;
    }
}