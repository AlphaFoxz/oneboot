package com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;
import org.springframework.lang.Nullable;

@Schema(name = "PsysMenuInfoDto", description = "系统菜单dto")
@Accessors(chain = true)
@Getter
@Setter
public class PsysMenuInfoDto {
    @Schema(name = "path", description = "路径")
    private String path;
    @Schema(name = "name", description = "名称")
    @Nullable
    private String name;
    @Schema(name = "meta", description = "元信息")
    private PsysMenuMetaDto meta;
    @Schema(name = "children", description = "子节点")
    @Nullable
    private List<PsysMenuInfoDto> children;
}