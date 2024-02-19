package com.github.alphafoxz.oneboot.preset_sys.gen.restl.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import com.github.alphafoxz.oneboot.core.standard.service.HttpController;
import java.util.Set;
import org.springframework.web.bind.annotation.RequestMapping;

// 数据鉴权包括dac和abac两个体系
// 具体设计可在wiki中查找
@RequestMapping({"/preset_sys/abac"})
@Tag(name = "PsysAbacApi", description = "ABAC接口")
public interface PsysAbacApi extends HttpController {
    @Operation(summary = "查询当前用户属性集合", responses = {
            @ApiResponse(description = "请求成功", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(description = "无权限", responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "参数无效", responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
    })
    public ResponseEntity<Set<String>> queryCurrentAttributes();

}