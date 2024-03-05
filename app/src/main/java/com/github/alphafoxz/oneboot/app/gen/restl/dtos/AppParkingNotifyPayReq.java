package com.github.alphafoxz.oneboot.app.gen.restl.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Schema(name = "AppParkingNotifyPayReq", description = "告知付款请求参数")
@Accessors(chain = true)
@Getter
@Setter
public class AppParkingNotifyPayReq {
    @Schema(name = "plate", description = "车牌号")
    private String plate;
    @Schema(name = "time", description = "格式yyyy-MM-dd HH:mm:ss")
    private String time;
    @Schema(name = "amount", description = "金额（元）")
    private Integer amount;
}