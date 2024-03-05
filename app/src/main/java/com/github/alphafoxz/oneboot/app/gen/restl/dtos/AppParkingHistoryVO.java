package com.github.alphafoxz.oneboot.app.gen.restl.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Schema(name = "AppParkingHistoryVO", description = "停车历史视图")
@Accessors(chain = true)
@Getter
@Setter
public class AppParkingHistoryVO {
    @Schema(name = "plate", description = "车牌号")
    private String plate;
    @Schema(name = "checkInTime", description = "入场时间")
    private String checkInTime;
    @Schema(name = "checkOutTime", description = "出场时间")
    private String checkOutTime;
    @Schema(name = "payAmount", description = "金额")
    private Integer payAmount;
}