package com.github.alphafoxz.oneboot.sdk.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import java.util.List;

@Schema(name = "SdkListRequestDto", description = "")
@Getter
public class SdkListRequestDto {
    @Schema(name = "id", description = "")
    private Long id;
    @Schema(name = "taskId", description = "")
    private Long taskId;
    @Schema(name = "data", description = "")
    private List<String> data;

    public SdkListRequestDto setId(Long id) {
        this.id = id;
        return this;
    }
    public SdkListRequestDto setTaskId(Long taskId) {
        this.taskId = taskId;
        return this;
    }
    public SdkListRequestDto setData(List<String> data) {
        this.data = data;
        return this;
    }
}