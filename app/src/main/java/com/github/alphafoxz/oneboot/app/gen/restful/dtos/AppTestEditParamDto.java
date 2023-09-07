package com.github.alphafoxz.oneboot.app.gen.restful.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;
import com.github.alphafoxz.oneboot.app.gen.restful.enums.AppTestStateEnum;
import java.util.Map;

// 测试请求参数体
@Schema(description = "AppTestEditParamDto")
@Data
public class AppTestEditParamDto {
    @Schema(description = "主键")
    private Long id;
    //状态枚举
    @Schema(description = "测试状态")
    private AppTestStateEnum testState;
    @Schema(description = "")
    private Map<String, String> optionMap;
    @Schema(description = "")
    private List<AppTestOtherInfoParamDto> otherInfoList;
}