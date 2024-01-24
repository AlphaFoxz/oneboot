package com.github.alphafoxz.oneboot.sdk.gen.restful.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import com.github.alphafoxz.oneboot.common.standard.framework.HttpController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.github.alphafoxz.oneboot.sdk.gen.restful.dtos.SdkStringResponseDto;

@RequestMapping({"/_sdk/info"})
@Tag(name = "SdkInfoApi", description = "Sdk模块基本信息接口")
public interface SdkInfoApi extends HttpController {
    @GetMapping(value = {"/rootPath"})
    @Operation(summary = "获取当前项目的硬盘根目录", responses = {
            @ApiResponse(description = "请求成功", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(description = "无权限", responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "参数无效", responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
    })
    public ResponseEntity<SdkStringResponseDto> rootPath();

}