package com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(name = "PsysUserTokenDto", description = "用户令牌结果")
@Getter
public class PsysUserTokenDto {
    @Schema(name = "accessToken", description = "访问令牌")
    private String accessToken;
    @Schema(name = "refreshToken", description = "刷新令牌")
    private String refreshToken;

    public PsysUserTokenDto setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }
    public PsysUserTokenDto setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }
}