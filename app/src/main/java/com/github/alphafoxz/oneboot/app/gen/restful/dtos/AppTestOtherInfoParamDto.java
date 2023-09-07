package com.github.alphafoxz.oneboot.app.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "AppTestOtherInfoParamDto")
@Data
public class AppTestOtherInfoParamDto {
    @Schema(description = "")
    private Long id;
    @Schema(description = "")
    private String name;
}