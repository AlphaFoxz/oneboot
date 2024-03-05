package com.github.alphafoxz.oneboot.app.gen.restl.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Schema(name = "AppParkingDailyRevenueVO", description = "日常营业额视图")
@Accessors(chain = true)
@Getter
@Setter
public class AppParkingDailyRevenueVO {
    @Schema(name = "date", description = "")
    private String date;
    @Schema(name = "revenue", description = "")
    private Integer revenue;
}