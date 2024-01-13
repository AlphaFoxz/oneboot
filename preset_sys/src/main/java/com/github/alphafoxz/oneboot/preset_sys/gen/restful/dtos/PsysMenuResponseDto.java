package com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;

@Schema(name = "PsysMenuResponseDto", description = "系统菜单响应体")
@Accessors(chain = true)
@Getter
@Setter
public class PsysMenuResponseDto {
    @Schema(name = "success", description = "是否成功")
    private Boolean success;
    @Schema(name = "data", description = "响应数据")
    private List<PsysMenuInfoDto> data;
}