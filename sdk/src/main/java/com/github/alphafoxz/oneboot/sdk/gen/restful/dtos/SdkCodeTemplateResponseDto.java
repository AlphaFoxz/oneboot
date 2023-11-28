package com.github.alphafoxz.oneboot.sdk.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import com.github.alphafoxz.oneboot.sdk.gen.restful.dtos.SdkCodeTemplateDto;

// 响应体的data字段是SdkCodeTemplateDto
@Schema(name = "SdkCodeTemplateResponseDto", description = "代码模板响应实体")
@Getter
public class SdkCodeTemplateResponseDto {
    @Schema(name = "id", description = "主键")
    private Long id;
    @Schema(name = "taskId", description = "任务id")
    private Long taskId;
    @Schema(name = "success", description = "是否成功")
    private Boolean success;
    @Schema(name = "message", description = "消息")
    private String message;
    @Schema(name = "data", description = "数据内容")
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