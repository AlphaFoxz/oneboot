package com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Schema(name = "PsysAccountDepartmentPageParam", description = "部门分页查询传参")
@Accessors(chain = true)
@Getter
@Setter
public class PsysAccountDepartmentPageParam {
    @Schema(name = "pageNum", description = "页码")
    private Integer pageNum;
    @Schema(name = "pageSize", description = "每页条数")
    private Integer pageSize;
    @Schema(name = "name", description = "名称")
    private String name;
    @Schema(name = "status", description = "状态")
    private Short status;
}