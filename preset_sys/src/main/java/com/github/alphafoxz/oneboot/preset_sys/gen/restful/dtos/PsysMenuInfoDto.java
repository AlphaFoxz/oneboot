package com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import java.util.List;

@Schema(name = "PsysMenuInfoDto", description = "系统菜单dto")
@Getter
public class PsysMenuInfoDto {
    @Schema(name = "path", description = "路径")
    private String path;
    @Schema(name = "name", description = "名称")
    private String name;
    @Schema(name = "meta", description = "元信息")
    private PsysMenuMetaDto meta;
    @Schema(name = "children", description = "子节点")
    private List<PsysMenuInfoDto> children;

    public PsysMenuInfoDto setPath(String path) {
        this.path = path;
        return this;
    }
    public PsysMenuInfoDto setName(String name) {
        this.name = name;
        return this;
    }
    public PsysMenuInfoDto setMeta(PsysMenuMetaDto meta) {
        this.meta = meta;
        return this;
    }
    public PsysMenuInfoDto setChildren(List<PsysMenuInfoDto> children) {
        this.children = children;
        return this;
    }
}