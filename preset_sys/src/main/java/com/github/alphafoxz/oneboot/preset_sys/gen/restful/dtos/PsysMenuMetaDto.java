package com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import java.util.List;

@Schema(name = "PsysMenuMetaDto", description = "系统菜单元信息")
@Getter
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

    public PsysMenuMetaDto setRank(Integer rank) {
        this.rank = rank;
        return this;
    }
    public PsysMenuMetaDto setIcon(String icon) {
        this.icon = icon;
        return this;
    }
    public PsysMenuMetaDto setAuths(List<String> auths) {
        this.auths = auths;
        return this;
    }
    public PsysMenuMetaDto setFrameSrc(String frameSrc) {
        this.frameSrc = frameSrc;
        return this;
    }
    public PsysMenuMetaDto setTitle(String title) {
        this.title = title;
        return this;
    }
    public PsysMenuMetaDto setShowLink(Boolean showLink) {
        this.showLink = showLink;
        return this;
    }
    public PsysMenuMetaDto setActivePath(String activePath) {
        this.activePath = activePath;
        return this;
    }
    public PsysMenuMetaDto setRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }
}