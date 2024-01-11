package com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Schema(name = "PsysAuthRouteDto", description = "路由信息")
@Accessors(chain = true)
@Getter
@Setter
public class PsysAuthRouteDto {
    @Schema(name = "path", description = "")
    private String path;
    @Schema(name = "name", description = "")
    private String name;
    @Schema(name = "meta", description = "")
    private PsysAuthRouteMeta meta;
    @Schema(name = "children", description = "")
    private PsysAuthRouteDto children;
}