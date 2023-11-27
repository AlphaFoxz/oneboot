package com.github.alphafoxz.oneboot.sdk.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import java.util.Map;

@Schema(name = "SdkMapRequestDto", description = "")
@Getter
public class SdkMapRequestDto {
    @Schema(name = "id", description = "")
    private Long id;
    @Schema(name = "taskId", description = "")
    private Long taskId;
    @Schema(name = "data", description = "")
    private Map<String, String> data;

    public SdkMapRequestDto setId(Long id) {
        this.id = id;
        return this;
    }
    public SdkMapRequestDto setTaskId(Long taskId) {
        this.taskId = taskId;
        return this;
    }
    public SdkMapRequestDto setData(Map<String, String> data) {
        this.data = data;
        return this;
    }
}