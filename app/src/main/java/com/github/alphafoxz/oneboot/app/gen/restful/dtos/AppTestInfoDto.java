package com.github.alphafoxz.oneboot.app.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

// 测试请求参数体
@Schema(name = "AppTestInfoDto", description = "请求参数体说明")
@Data
public class AppTestInfoDto {
    @Schema(name = "id", description = "主键")
    private Long id;
    @Schema(name = "testJson", description = "")
    private String testJson;
    @Schema(name = "testDate", description = "")
    private String testDate;
    @Schema(name = "testTimestamp", description = "")
    private String testTimestamp;
    @Schema(name = "testBool", description = "")
    private Boolean testBool;
    @Schema(name = "testDouble", description = "")
    private Double testDouble;
    @Schema(name = "testFloat", description = "")
    private Double testFloat;
    @Schema(name = "testTimestamptz", description = "")
    private String testTimestamptz;
    @Schema(name = "testTime", description = "")
    private String testTime;
    @Schema(name = "testTimetz", description = "")
    private String testTimetz;
    @Schema(name = "testDaterange", description = "")
    private String testDaterange;
    @Schema(name = "testVarchar50", description = "")
    private String testVarchar50;
}