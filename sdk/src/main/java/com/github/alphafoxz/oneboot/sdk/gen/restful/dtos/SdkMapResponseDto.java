package com.github.alphafoxz.oneboot.sdk.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import java.util.Map;

// 响应体的data字段是map
@Schema(name = "SdkMapResponseDto", description = "字符串map响应实体")
@Getter
public class SdkMapResponseDto {
    @Schema(name = "id", description = "主键")
    private Long id;
    @Schema(name = "taskId", description = "任务id")
    private Long taskId;
    @Schema(name = "success", description = "是否成功")
    private Boolean success;
    @Schema(name = "message", description = "消息")
    private String message;
    @Schema(name = "data", description = "数据内容")
    private Map<String, String> data;

    public SdkMapResponseDto setId(Long id) {
        this.id = id;
        return this;
    }
    public SdkMapResponseDto setTaskId(Long taskId) {
        this.taskId = taskId;
        return this;
    }
    public SdkMapResponseDto setSuccess(Boolean success) {
        this.success = success;
        return this;
    }
    public SdkMapResponseDto setMessage(String message) {
        this.message = message;
        return this;
    }
    public SdkMapResponseDto setData(Map<String, String> data) {
        this.data = data;
        return this;
    }
}