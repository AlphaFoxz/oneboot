package com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Schema(name = "PsysAccountUserPageParam", description = "用户分页查询传参")
@Accessors(chain = true)
@Getter
@Setter
public class PsysAccountUserPageParam {
    @Schema(name = "pageNum", description = "页码")
    private Integer pageNum;
    @Schema(name = "pageSize", description = "每页条数")
    private Integer pageSize;
    @Schema(name = "deptId", description = "名称")
    private Long deptId;
    @Schema(name = "username", description = "用户名")
    private String username;
    @Schema(name = "phone", description = "手机号")
    private String phone;
    @Schema(name = "status", description = "状态")
    private Short status;
}