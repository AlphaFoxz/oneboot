package com.github.alphafoxz.oneboot.sdk.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

// 响应体的data字段是long
@Schema(name = "SdkLongResponseDto", description = "长整型响应实体")
@Getter
public class SdkLongResponseDto {
    @Schema(name = "id", description = "主键")
    private Long id;
    @Schema(name = "taskId", description = "任务id")
    private Long taskId;
    @Schema(name = "success", description = "是否成功")
    private Boolean success;
    @Schema(name = "message", description = "消息")
    private String message;
    @Schema(name = "data", description = "数据内容")
    private Long data;

    public SdkLongResponseDto setId(Long id) {
        this.id = id;
        return this;
    }
    public SdkLongResponseDto setTaskId(Long taskId) {
        this.taskId = taskId;
        return this;
    }
    public SdkLongResponseDto setSuccess(Boolean success) {
        this.success = success;
        return this;
    }
    public SdkLongResponseDto setMessage(String message) {
        this.message = message;
        return this;
    }
    public SdkLongResponseDto setData(Long data) {
        this.data = data;
        return this;
    }
}