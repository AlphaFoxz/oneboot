package com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;
import org.springframework.lang.Nullable;

@Schema(name = "PsysAuthRouteMeta", description = "路由元数据")
@Accessors(chain = true)
@Getter
@Setter
public class PsysAuthRouteMeta {
    @Schema(name = "icon", description = "图标")
    @Nullable
    private String icon;
    @Schema(name = "title", description = "标题")
    private String title;
    @Schema(name = "name", description = "名称")
    @Nullable
    private String name;
    @Schema(name = "rank", description = "排序")
    @Nullable
    private Integer rank;
    @Schema(name = "roles", description = "权限标识")
    @Nullable
    private List<String> roles;
}