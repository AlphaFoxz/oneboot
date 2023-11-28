package com.github.alphafoxz.oneboot.sdk.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import com.github.alphafoxz.oneboot.sdk.gen.restful.dtos.SdkCodeTemplateDto;

@Schema(name = "SdkCodeTemplateRequestDto", description = "")
@Getter
public class SdkCodeTemplateRequestDto {
    @Schema(name = "id", description = "")
    private Long id;
    @Schema(name = "taskId", description = "")
    private Long taskId;
    @Schema(name = "data", description = "")
    private SdkCodeTemplateDto data;

    public SdkCodeTemplateRequestDto setId(Long id) {
        this.id = id;
        return this;
    }
    public SdkCodeTemplateRequestDto setTaskId(Long taskId) {
        this.taskId = taskId;
        return this;
    }
    public SdkCodeTemplateRequestDto setData(SdkCodeTemplateDto data) {
        this.data = data;
        return this;
    }
}