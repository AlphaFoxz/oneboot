package com.github.alphafoxz.oneboot.sdk.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import com.github.alphafoxz.oneboot.sdk.gen.restful.dtos.SdkFileInfoDto;

// 响应体的data字段是SdkFileInfoDto
@Schema(name = "SdkFileTreeResponseDto", description = "文件树响应实体")
@Getter
public class SdkFileTreeResponseDto {
    @Schema(name = "id", description = "主键")
    private Long id;
    @Schema(name = "taskId", description = "任务id")
    private Long taskId;
    @Schema(name = "success", description = "是否成功")
    private Boolean success;
    @Schema(name = "message", description = "消息")
    private String message;
    @Schema(name = "data", description = "数据内容")
    private SdkFileInfoDto data;

    public SdkFileTreeResponseDto setId(Long id) {
        this.id = id;
        return this;
    }
    public SdkFileTreeResponseDto setTaskId(Long taskId) {
        this.taskId = taskId;
        return this;
    }
    public SdkFileTreeResponseDto setSuccess(Boolean success) {
        this.success = success;
        return this;
    }
    public SdkFileTreeResponseDto setMessage(String message) {
        this.message = message;
        return this;
    }
    public SdkFileTreeResponseDto setData(SdkFileInfoDto data) {
        this.data = data;
        return this;
    }
}