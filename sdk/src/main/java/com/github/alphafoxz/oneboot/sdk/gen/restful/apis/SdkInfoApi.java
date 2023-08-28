package com.github.alphafoxz.oneboot.sdk.gen.restful.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkListResponseDto;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkStringResponseDto;

@Tag(name = "SdkInfoApi", description = "Sdk模块基本信息接口")
public interface SdkInfoApi {
    @Operation(summary = "获取当前项目的硬盘根目录")
    public ResponseEntity<SdkStringResponseDto> rootPath();

    @Operation(summary = "检查项目错误")
    public ResponseEntity<SdkListResponseDto> checkThriftErr();

    @Operation(summary = "检查RestApi实现情况")
    public ResponseEntity<SdkListResponseDto> checkRestApiImplements();

    @Operation(summary = "检查Rpc实现情况")
    public ResponseEntity<SdkListResponseDto> checkRpcImplements();

}