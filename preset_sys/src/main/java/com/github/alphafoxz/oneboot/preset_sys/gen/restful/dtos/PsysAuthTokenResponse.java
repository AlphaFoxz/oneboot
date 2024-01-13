package com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Schema(name = "PsysAuthTokenResponse", description = "用户信息响应体")
@Accessors(chain = true)
@Getter
@Setter
public class PsysAuthTokenResponse {
    @Schema(name = "success", description = "是否成功")
    private Boolean success;
    @Schema(name = "data", description = "响应数据")
    private PsysAuthTokenInfoDto data;
}