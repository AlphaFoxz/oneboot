package com.github.alphafoxz.oneboot.app.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;
import com.github.alphafoxz.oneboot.app.gen.restful.enums.AppTestStateEnum;
import java.util.Map;

// 测试请求参数体
@Schema(name = "AppTestEditParamDto", description = "请求参数体说明")
@Data
public class AppTestEditParamDto {
    @Schema(name = "id", description = "主键")
    private Long id;
    //状态枚举
    @Schema(name = "testState", description = "测试状态")
    private AppTestStateEnum testState;
    @Schema(name = "optionMap", description = "")
    private Map<String, String> optionMap;
    @Schema(name = "otherInfoList", description = "")
    private List<AppTestOtherInfoParamDto> otherInfoList;
}