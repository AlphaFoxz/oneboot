package com.github.alphafoxz.oneboot.sdk.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import com.github.alphafoxz.oneboot.sdk.gen.restful.dtos.SdkCodeTemplateDto;

// 响应体的data字段是SdkCodeTemplateDto
@Schema(name = "SdkCodeTemplateResponseDto", description = "代码模板响应实体")
@Accessors(chain = true)
@Getter
@Setter
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
}