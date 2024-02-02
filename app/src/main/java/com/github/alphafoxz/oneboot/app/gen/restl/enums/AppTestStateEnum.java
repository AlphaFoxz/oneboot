package com.github.alphafoxz.oneboot.app.gen.restl.enums;

import com.github.alphafoxz.oneboot.core.standard.restful.RestfulEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Schema(description = "测试枚举响应体")
@AllArgsConstructor
@Getter
// 测试枚举
public enum AppTestStateEnum implements RestfulEnum {
    /**状态1*/
    STATE1(0),
    // 状态2
    STATE2(1),
    STATE3(2);

    private final int value;
}