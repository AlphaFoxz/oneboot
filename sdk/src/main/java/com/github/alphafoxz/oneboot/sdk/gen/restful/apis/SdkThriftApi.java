package com.github.alphafoxz.oneboot.sdk.gen.restful.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import com.github.alphafoxz.oneboot.common.interfaces.framework.HttpController;
import io.swagger.v3.oas.annotations.Parameter;
import com.github.alphafoxz.oneboot.sdk.gen.restful.dtos.SdkCodeTemplateResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.github.alphafoxz.oneboot.sdk.gen.restful.dtos.SdkLongResponseDto;
import com.github.alphafoxz.oneboot.sdk.gen.restful.dtos.SdkStringResponseDto;

@RequestMapping({"/_sdk/thrift"})
@Tag(name = "SdkThriftApi", description = "thrift功能接口（rpc跨语言通信）")
public interface SdkThriftApi extends HttpController {
    @GetMapping({"/getServerPort"})
    @Operation(summary = "获取当前thrift服务端口", responses = {
            @ApiResponse(description = "请求成功", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(description = "无权限", responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "参数无效", responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
    })
    public ResponseEntity<SdkLongResponseDto> getServerPort();

    @GetMapping({"/getExecutableFilePath"})
    @Operation(summary = "获取thrift可执行文件路径", responses = {
            @ApiResponse(description = "请求成功", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(description = "无权限", responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "参数无效", responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
    })
    public ResponseEntity<SdkStringResponseDto> getExecutableFilePath();

    @GetMapping({"/getTemplateContentByPath"})
    @Operation(summary = "根据文件路径获取模板内容", responses = {
            @ApiResponse(description = "请求成功", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(description = "无权限", responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "参数无效", responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
    })
    public ResponseEntity<SdkCodeTemplateResponseDto> getTemplateContentByPath(
            @Parameter(description = "") String filePath
    );

}