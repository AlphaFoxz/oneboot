package com.github.alphafoxz.oneboot.sdk.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

// 响应体的data字段是double
@Schema(name = "SdkDoubleResponseDto", description = "双精度小数响应实体")
@Getter
public class SdkDoubleResponseDto {
    @Schema(name = "id", description = "主键")
    private Long id;
    @Schema(name = "taskId", description = "任务id")
    private Long taskId;
    @Schema(name = "success", description = "是否成功")
    private Boolean success;
    @Schema(name = "message", description = "消息")
    private String message;
    @Schema(name = "data", description = "数据内容")
    private Double data;

    public SdkDoubleResponseDto setId(Long id) {
        this.id = id;
        return this;
    }
    public SdkDoubleResponseDto setTaskId(Long taskId) {
        this.taskId = taskId;
        return this;
    }
    public SdkDoubleResponseDto setSuccess(Boolean success) {
        this.success = success;
        return this;
    }
    public SdkDoubleResponseDto setMessage(String message) {
        this.message = message;
        return this;
    }
    public SdkDoubleResponseDto setData(Double data) {
        this.data = data;
        return this;
    }
}