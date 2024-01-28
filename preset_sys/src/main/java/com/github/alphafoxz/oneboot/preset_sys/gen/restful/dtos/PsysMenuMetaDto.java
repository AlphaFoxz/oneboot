package com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.enums.PsysAuthRouteRankEnum;
import org.springframework.lang.Nullable;

@Schema(name = "PsysMenuMetaDto", description = "系统菜单元信息")
@Accessors(chain = true)
@Getter
@Setter
public class PsysMenuMetaDto {
    /**
     * @see PsysAuthRouteRankEnum
     */
    @Schema(name = "rank", description = "类型")
    @Nullable
    private Integer rank;
    @Schema(name = "icon", description = "标题")
    @Nullable
    private String icon;
    @Schema(name = "auths", description = "权限")
    @Nullable
    private List<String> auths;
    @Schema(name = "frameSrc", description = "iframe路径")
    @Nullable
    private String frameSrc;
    @Schema(name = "title", description = "标题")
    @Nullable
    private String title;
    @Schema(name = "showLink", description = "显示链接")
    @Nullable
    private Boolean showLink;
    @Schema(name = "activePath", description = "启用路径")
    @Nullable
    private String activePath;
    @Schema(name = "roles", description = "角色")
    @Nullable
    private List<String> roles;
}