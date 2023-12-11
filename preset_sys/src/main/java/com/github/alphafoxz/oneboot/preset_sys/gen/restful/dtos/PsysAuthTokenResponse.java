package com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(name = "PsysAuthTokenResponse", description = "用户信息响应体")
@Getter
public class PsysAuthTokenResponse {
    @Schema(name = "success", description = "是否成功")
    private Boolean success;
    @Schema(name = "data", description = "响应数据")
    private PsysAuthTokenInfoDto data;

    public PsysAuthTokenResponse setSuccess(Boolean success) {
        this.success = success;
        return this;
    }
    public PsysAuthTokenResponse setData(PsysAuthTokenInfoDto data) {
        this.data = data;
        return this;
    }
}