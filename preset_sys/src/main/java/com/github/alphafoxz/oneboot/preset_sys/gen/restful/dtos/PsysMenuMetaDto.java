package com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;

@Schema(name = "PsysMenuMetaDto", description = "系统菜单元信息")
@Accessors(chain = true)
@Getter
@Setter
public class PsysMenuMetaDto {
    @Schema(name = "rank", description = "类型")
    private Integer rank;
    @Schema(name = "icon", description = "标题")
    private String icon;
    @Schema(name = "auths", description = "权限")
    private List<String> auths;
    @Schema(name = "frameSrc", description = "iframe路径")
    private String frameSrc;
    @Schema(name = "title", description = "标题")
    private String title;
    @Schema(name = "showLink", description = "显示链接")
    private Boolean showLink;
    @Schema(name = "activePath", description = "启用路径")
    private String activePath;
    @Schema(name = "roles", description = "角色")
    private List<String> roles;
}