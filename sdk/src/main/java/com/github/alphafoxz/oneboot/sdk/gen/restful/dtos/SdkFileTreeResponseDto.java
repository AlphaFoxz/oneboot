package com.github.alphafoxz.oneboot.sdk.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.github.alphafoxz.oneboot.sdk.gen.restful.dtos.SdkFileInfoDto;

@Schema(name = "SdkFileTreeResponseDto", description = "")
@Data
public class SdkFileTreeResponseDto {
    @Schema(name = "id", description = "")
    private Long id;
    @Schema(name = "taskId", description = "")
    private Long taskId;
    @Schema(name = "success", description = "")
    private Boolean success;
    @Schema(name = "message", description = "")
    private String message;
    @Schema(name = "data", description = "")
    private SdkFileInfoDto data;
}