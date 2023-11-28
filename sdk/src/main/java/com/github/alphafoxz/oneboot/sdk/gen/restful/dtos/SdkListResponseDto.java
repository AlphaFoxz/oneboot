package com.github.alphafoxz.oneboot.sdk.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import java.util.List;

// 响应体的data字段是list
@Schema(name = "SdkListResponseDto", description = "字符串列表响应实体")
@Getter
public class SdkListResponseDto {
    @Schema(name = "id", description = "主键")
    private Long id;
    @Schema(name = "taskId", description = "任务id")
    private Long taskId;
    @Schema(name = "success", description = "是否成功")
    private Boolean success;
    @Schema(name = "message", description = "消息")
    private String message;
    @Schema(name = "data", description = "数据内容")
    private List<String> data;

    public SdkListResponseDto setId(Long id) {
        this.id = id;
        return this;
    }
    public SdkListResponseDto setTaskId(Long taskId) {
        this.taskId = taskId;
        return this;
    }
    public SdkListResponseDto setSuccess(Boolean success) {
        this.success = success;
        return this;
    }
    public SdkListResponseDto setMessage(String message) {
        this.message = message;
        return this;
    }
    public SdkListResponseDto setData(List<String> data) {
        this.data = data;
        return this;
    }
}