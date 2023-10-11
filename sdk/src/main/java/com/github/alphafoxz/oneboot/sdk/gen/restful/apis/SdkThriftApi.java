package com.github.alphafoxz.oneboot.sdk.gen.restful.apis;

import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "获取当前thrift服务端口")
    public ResponseEntity<SdkLongResponseDto> getServerPort();

    @GetMapping({"/getExecutableFilePath"})
    @Operation(summary = "获取thrift可执行文件路径")
    public ResponseEntity<SdkStringResponseDto> getExecutableFilePath();

    @GetMapping({"/getTemplateContentByPath"})
    @Operation(summary = "根据文件路径获取thrift模板内容")
    public ResponseEntity<SdkCodeTemplateResponseDto> getTemplateContentByPath(
            @Parameter(description = "") String filePath
    );

}