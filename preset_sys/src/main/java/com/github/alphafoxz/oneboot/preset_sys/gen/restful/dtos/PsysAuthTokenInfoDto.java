package com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import java.util.List;

@Schema(name = "PsysAuthTokenInfoDto", description = "用户令牌结果")
@Getter
public class PsysAuthTokenInfoDto {
    @Schema(name = "username", description = "用户名")
    private String username;
    @Schema(name = "roles", description = "当前登录用户的角色")
    private List<String> roles;
    @Schema(name = "accessToken", description = "访问令牌")
    private String accessToken;
    @Schema(name = "refreshToken", description = "刷新令牌")
    private String refreshToken;
    @Schema(name = "expires", description = "accessToken的过期时间（格式'yyyy/MM/dd HH:mm:ss'）")
    private String expires;

    public PsysAuthTokenInfoDto setUsername(String username) {
        this.username = username;
        return this;
    }
    public PsysAuthTokenInfoDto setRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }
    public PsysAuthTokenInfoDto setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }
    public PsysAuthTokenInfoDto setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }
    public PsysAuthTokenInfoDto setExpires(String expires) {
        this.expires = expires;
        return this;
    }
}