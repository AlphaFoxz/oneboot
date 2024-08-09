package com.github.alphafoxz.oneboot.preset_sys.gen.restl.dtos;

import com.github.alphafoxz.oneboot.preset_sys.gen.restl.dtos._compile_only.PsysAuthTokenInfoDtoFields;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Schema(name = "PsysAuthTokenInfoDto", description = "用户令牌结果")
@Accessors(chain = true)
@Getter
@Setter
public class PsysAuthTokenInfoDto {
    @Schema(name = PsysAuthTokenInfoDtoFields.USERNAME, description = "用户名")
    private String username;
    @Schema(name = PsysAuthTokenInfoDtoFields.ROLES, description = "当前登录用户的角色")
    private List<String> roles;
    @Schema(name = PsysAuthTokenInfoDtoFields.ACCESS_TOKEN, description = "访问令牌")
    private String accessToken;
    @Schema(name = PsysAuthTokenInfoDtoFields.REFRESH_TOKEN, description = "刷新令牌")
    private String refreshToken;
    @Schema(name = PsysAuthTokenInfoDtoFields.EXPIRES, description = "accessToken的过期时间（格式'yyyy/MM/dd HH:mm:ss'）")
    private String expires;
}