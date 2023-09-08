package com.github.alphafoxz.oneboot.app.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "AppTestOtherInfoParamDto", description = "")
@Data
public class AppTestOtherInfoParamDto {
    @Schema(name = "id", description = "")
    private Long id;
    @Schema(name = "name", description = "")
    private String name;
}