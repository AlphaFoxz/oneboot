package com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(name = "PsysAuthUserInfoDto", description = "用户信息")
@Getter
public class PsysAuthUserInfoDto {
    @Schema(name = "id", description = "主键")
    private Long id;
    @Schema(name = "username", description = "用户名称")
    private String username;
    @Schema(name = "nickname", description = "用户昵称")
    private String nickname;
    @Schema(name = "avatar", description = "用户头像")
    private String avatar;
    @Schema(name = "sex", description = "性别")
    private Integer sex;
    @Schema(name = "phone", description = "手机号码")
    private String phone;
    @Schema(name = "email", description = "电子邮箱")
    private String email;
    @Schema(name = "remark", description = "备注")
    private String remark;
    @Schema(name = "status", description = "状态")
    private Integer status;
    @Schema(name = "createTime", description = "创建时间")
    private Long createTime;

    public PsysAuthUserInfoDto setId(Long id) {
        this.id = id;
        return this;
    }
    public PsysAuthUserInfoDto setUsername(String username) {
        this.username = username;
        return this;
    }
    public PsysAuthUserInfoDto setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }
    public PsysAuthUserInfoDto setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }
    public PsysAuthUserInfoDto setSex(Integer sex) {
        this.sex = sex;
        return this;
    }
    public PsysAuthUserInfoDto setPhone(String phone) {
        this.phone = phone;
        return this;
    }
    public PsysAuthUserInfoDto setEmail(String email) {
        this.email = email;
        return this;
    }
    public PsysAuthUserInfoDto setRemark(String remark) {
        this.remark = remark;
        return this;
    }
    public PsysAuthUserInfoDto setStatus(Integer status) {
        this.status = status;
        return this;
    }
    public PsysAuthUserInfoDto setCreateTime(Long createTime) {
        this.createTime = createTime;
        return this;
    }
}