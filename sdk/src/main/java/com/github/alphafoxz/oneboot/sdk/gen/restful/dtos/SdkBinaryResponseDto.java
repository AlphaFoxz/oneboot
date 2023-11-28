package com.github.alphafoxz.oneboot.sdk.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

// 响应体的data字段是binary
@Schema(name = "SdkBinaryResponseDto", description = "二进制响应实体")
@Getter
public class SdkBinaryResponseDto {
    @Schema(name = "id", description = "主键")
    private Long id;
    @Schema(name = "taskId", description = "任务id")
    private Long taskId;
    @Schema(name = "success", description = "是否成功")
    private Boolean success;
    @Schema(name = "message", description = "消息")
    private String message;
    @Schema(name = "data", description = "数据内容")
    private String data;

    public SdkBinaryResponseDto setId(Long id) {
        this.id = id;
        return this;
    }
    public SdkBinaryResponseDto setTaskId(Long taskId) {
        this.taskId = taskId;
        return this;
    }
    public SdkBinaryResponseDto setSuccess(Boolean success) {
        this.success = success;
        return this;
    }
    public SdkBinaryResponseDto setMessage(String message) {
        this.message = message;
        return this;
    }
    public SdkBinaryResponseDto setData(String data) {
        this.data = data;
        return this;
    }
}