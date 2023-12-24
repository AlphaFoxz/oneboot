package com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(name = "PsysAuthUserPageParam", description = "用户分页查询传参")
@Getter
public class PsysAuthUserPageParam {
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

    public PsysAuthUserPageParam setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
        return this;
    }
    public PsysAuthUserPageParam setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }
    public PsysAuthUserPageParam setDeptId(Long deptId) {
        this.deptId = deptId;
        return this;
    }
    public PsysAuthUserPageParam setUsername(String username) {
        this.username = username;
        return this;
    }
    public PsysAuthUserPageParam setPhone(String phone) {
        this.phone = phone;
        return this;
    }
    public PsysAuthUserPageParam setStatus(Short status) {
        this.status = status;
        return this;
    }
}