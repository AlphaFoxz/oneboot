package com.github.alphafoxz.oneboot.app.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

// 测试请求参数体
@Schema(name = "AppTestInfoDto", description = "请求参数体说明")
@Getter
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

    public AppTestInfoDto setId(Long id) {
        this.id = id;
        return this;
    }
    public AppTestInfoDto setTestJson(String testJson) {
        this.testJson = testJson;
        return this;
    }
    public AppTestInfoDto setTestDate(String testDate) {
        this.testDate = testDate;
        return this;
    }
    public AppTestInfoDto setTestTimestamp(String testTimestamp) {
        this.testTimestamp = testTimestamp;
        return this;
    }
    public AppTestInfoDto setTestBool(Boolean testBool) {
        this.testBool = testBool;
        return this;
    }
    public AppTestInfoDto setTestDouble(Double testDouble) {
        this.testDouble = testDouble;
        return this;
    }
    public AppTestInfoDto setTestFloat(Double testFloat) {
        this.testFloat = testFloat;
        return this;
    }
    public AppTestInfoDto setTestTimestamptz(String testTimestamptz) {
        this.testTimestamptz = testTimestamptz;
        return this;
    }
    public AppTestInfoDto setTestTime(String testTime) {
        this.testTime = testTime;
        return this;
    }
    public AppTestInfoDto setTestTimetz(String testTimetz) {
        this.testTimetz = testTimetz;
        return this;
    }
    public AppTestInfoDto setTestDaterange(String testDaterange) {
        this.testDaterange = testDaterange;
        return this;
    }
    public AppTestInfoDto setTestVarchar50(String testVarchar50) {
        this.testVarchar50 = testVarchar50;
        return this;
    }
}