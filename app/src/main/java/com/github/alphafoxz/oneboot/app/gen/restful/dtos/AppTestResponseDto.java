package com.github.alphafoxz.oneboot.app.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "AppTestResponseDto", description = "")
@Data
public class AppTestResponseDto {
    @Schema(name = "success", description = "")
    private Boolean success;
    @Schema(name = "msg", description = "")
    private String msg;
    @Schema(name = "entity", description = "")
    private AppTestEntityDto entity;
}