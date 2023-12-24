package com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(name = "PsysAuthDepartmentPageParam", description = "部门分页查询传参")
@Getter
public class PsysAuthDepartmentPageParam {
    @Schema(name = "pageNum", description = "页码")
    private Integer pageNum;
    @Schema(name = "pageSize", description = "每页条数")
    private Integer pageSize;
    @Schema(name = "name", description = "名称")
    private String name;
    @Schema(name = "status", description = "状态")
    private Short status;

    public PsysAuthDepartmentPageParam setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
        return this;
    }
    public PsysAuthDepartmentPageParam setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }
    public PsysAuthDepartmentPageParam setName(String name) {
        this.name = name;
        return this;
    }
    public PsysAuthDepartmentPageParam setStatus(Short status) {
        this.status = status;
        return this;
    }
}