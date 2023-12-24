package com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(name = "PsysAuthRolePageParam", description = "角色分页查询传参")
@Getter
public class PsysAuthRolePageParam {
    @Schema(name = "pageNum", description = "页码")
    private Integer pageNum;
    @Schema(name = "pageSize", description = "每页条数")
    private Integer pageSize;
    @Schema(name = "name", description = "角色名称")
    private String name;
    @Schema(name = "code", description = "角色标识")
    private String code;
    @Schema(name = "status", description = "状态")
    private Short status;

    public PsysAuthRolePageParam setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
        return this;
    }
    public PsysAuthRolePageParam setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }
    public PsysAuthRolePageParam setName(String name) {
        this.name = name;
        return this;
    }
    public PsysAuthRolePageParam setCode(String code) {
        this.code = code;
        return this;
    }
    public PsysAuthRolePageParam setStatus(Short status) {
        this.status = status;
        return this;
    }
}