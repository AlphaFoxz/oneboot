package com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(name = "PsysAuthRoleInfoDto", description = "角色信息")
@Getter
public class PsysAuthRoleInfoDto {
    @Schema(name = "id", description = "主键")
    private Long id;
    @Schema(name = "createTime", description = "创建时间")
    private Long createTime;
    @Schema(name = "updateTime", description = "更新时间")
    private Long updateTime;
    @Schema(name = "name", description = "角色名称")
    private String name;
    @Schema(name = "code", description = "角色编码")
    private String code;
    @Schema(name = "status", description = "状态")
    private Short status;
    @Schema(name = "remark", description = "备注")
    private String remark;

    public PsysAuthRoleInfoDto setId(Long id) {
        this.id = id;
        return this;
    }
    public PsysAuthRoleInfoDto setCreateTime(Long createTime) {
        this.createTime = createTime;
        return this;
    }
    public PsysAuthRoleInfoDto setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
        return this;
    }
    public PsysAuthRoleInfoDto setName(String name) {
        this.name = name;
        return this;
    }
    public PsysAuthRoleInfoDto setCode(String code) {
        this.code = code;
        return this;
    }
    public PsysAuthRoleInfoDto setStatus(Short status) {
        this.status = status;
        return this;
    }
    public PsysAuthRoleInfoDto setRemark(String remark) {
        this.remark = remark;
        return this;
    }
}