package com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Schema(name = "PsysAccountDepartmentInfoDto", description = "部门信息")
@Accessors(chain = true)
@Getter
@Setter
public class PsysAccountDepartmentInfoDto {
    @Schema(name = "id", description = "主键")
    private Long id;
    @Schema(name = "deptName", description = "名称")
    private String deptName;
}