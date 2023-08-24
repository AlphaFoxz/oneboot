package com.github.alphafoxz.oneboot.app.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "TestResponseDto")
@Data
public class TestResponseDto {
    @Schema(description = "")
    private Boolean success;
    @Schema(description = "")
    private String msg;
    @Schema(description = "")
    private TestEntityDto entity;
}