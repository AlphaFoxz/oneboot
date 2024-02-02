package com.github.alphafoxz.oneboot.preset_sys.gen.restl.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.lang.Nullable;

@Schema(name = "PsysAccountDepartmentPageParam", description = "部门分页查询传参")
@Accessors(chain = true)
@Getter
@Setter
public class PsysAccountDepartmentPageParam {
    @Schema(name = "pageNum", description = "页码")
    @Nullable
    private Integer pageNum;
    @Schema(name = "pageSize", description = "每页条数")
    @Nullable
    private Integer pageSize;
    @Schema(name = "name", description = "名称")
    @Nullable
    private String name;
    @Schema(name = "status", description = "状态")
    @Nullable
    private Short status;
}