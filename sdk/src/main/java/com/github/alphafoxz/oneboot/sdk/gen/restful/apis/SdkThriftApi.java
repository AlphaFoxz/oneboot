package com.github.alphafoxz.oneboot.sdk.gen.restful.apis;

import com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkLongResponseDto;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkStringResponseDto;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkThriftTemplateResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "SdkThriftApi", description = "thrift功能接口（rpc跨语言通信）")
public interface SdkThriftApi {
    @Operation(summary = "获取当前thrift服务端口")
    public ResponseEntity<SdkLongResponseDto> getServerPort();

    @Operation(summary = "获取thrift可执行文件路径")
    public ResponseEntity<SdkStringResponseDto> getExecutableFilePath();

    @Operation(summary = "根据文件路径获取thrift模板内容")
    public ResponseEntity<SdkThriftTemplateResponseDto> getTemplateContentByPath(@Parameter(description = "文件路径") String filePath);

}
