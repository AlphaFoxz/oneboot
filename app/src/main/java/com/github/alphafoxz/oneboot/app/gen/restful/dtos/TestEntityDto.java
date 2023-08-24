package com.github.alphafoxz.oneboot.app.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "TestEntityDto")
@Data
public class TestEntityDto {
    @Schema(description = "")
    private Long id;
    @Schema(description = "")
    private String name;
}