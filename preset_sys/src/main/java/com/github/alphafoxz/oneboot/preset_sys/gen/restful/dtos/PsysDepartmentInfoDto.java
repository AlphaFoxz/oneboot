package com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(name = "PsysDepartmentInfoDto", description = "部门信息")
@Getter
public class PsysDepartmentInfoDto {
    @Schema(name = "id", description = "主键")
    private Long id;
    @Schema(name = "name", description = "名称")
    private String name;

    public PsysDepartmentInfoDto setId(Long id) {
        this.id = id;
        return this;
    }
    public PsysDepartmentInfoDto setName(String name) {
        this.name = name;
        return this;
    }
}