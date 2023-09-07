package com.github.alphafoxz.oneboot.app.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "AppTestResponseDto")
@Data
public class AppTestResponseDto {
    @Schema(description = "")
    private Boolean success;
    @Schema(description = "")
    private String msg;
    @Schema(description = "")
    private AppTestEntityDto entity;
}