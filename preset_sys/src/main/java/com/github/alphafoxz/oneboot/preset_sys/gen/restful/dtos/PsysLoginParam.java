package com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(name = "PsysLoginParam", description = "用户名密码登录参数")
@Getter
public class PsysLoginParam {
    @Schema(name = "username", description = "用户名")
    private String username;
    @Schema(name = "password", description = "密码")
    private String password;
    @Schema(name = "verifyCode", description = "验证码")
    private String verifyCode;

    public PsysLoginParam setUsername(String username) {
        this.username = username;
        return this;
    }
    public PsysLoginParam setPassword(String password) {
        this.password = password;
        return this;
    }
    public PsysLoginParam setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
        return this;
    }
}