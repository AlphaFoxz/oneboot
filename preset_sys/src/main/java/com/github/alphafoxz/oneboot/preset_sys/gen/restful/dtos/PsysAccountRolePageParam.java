package com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.lang.Nullable;

@Schema(name = "PsysAccountRolePageParam", description = "角色分页查询传参")
@Accessors(chain = true)
@Getter
@Setter
public class PsysAccountRolePageParam {
    @Schema(name = "pageNum", description = "页码")
    @Nullable
    private Integer pageNum;
    @Schema(name = "pageSize", description = "每页条数")
    @Nullable
    private Integer pageSize;
    @Schema(name = "name", description = "角色名称")
    private String name;
    @Schema(name = "code", description = "角色标识")
    @Nullable
    private String code;
    @Schema(name = "status", description = "状态")
    @Nullable
    private Short status;
}