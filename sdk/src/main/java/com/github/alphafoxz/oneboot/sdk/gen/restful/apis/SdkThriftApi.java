package com.github.alphafoxz.oneboot.sdk.gen.restful.apis;

import com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkLongResponseDto;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkStringResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "thrift功能接口（rpc跨语言通信）", description = "SdkThriftApi")
public interface SdkThriftApi {
    @Operation(summary = "获取当前thrift服务端口")
    public ResponseEntity<SdkLongResponseDto> getServerPort();

    @Operation(summary = "获取thrift可执行文件路径")
    public ResponseEntity<SdkStringResponseDto> getExecutableFilePath();

    @Operation(summary = "根据文件路径获取thrift模板内容")
    public ResponseEntity<SdkStringResponseDto> getTemplateContentByPath(String filePath);
}
