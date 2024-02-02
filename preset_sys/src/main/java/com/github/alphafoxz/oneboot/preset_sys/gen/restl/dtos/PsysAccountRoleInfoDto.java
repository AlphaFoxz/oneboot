package com.github.alphafoxz.oneboot.preset_sys.gen.restl.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Schema(name = "PsysAccountRoleInfoDto", description = "角色信息")
@Accessors(chain = true)
@Getter
@Setter
public class PsysAccountRoleInfoDto {
    @Schema(name = "id", description = "主键")
    private Long id;
    @Schema(name = "createTime", description = "创建时间")
    private Long createTime;
    @Schema(name = "updateTime", description = "更新时间")
    private Long updateTime;
    @Schema(name = "roleName", description = "角色名称")
    private String roleName;
    @Schema(name = "roleCode", description = "角色编码")
    private String roleCode;
    @Schema(name = "status", description = "状态")
    private Integer status;
    @Schema(name = "remark", description = "备注")
    private String remark;
}