package com.github.alphafoxz.oneboot.sdk.gen.restful.apis;

import com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkListResponseDto;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkStringResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Sdk模块基本信息接口", description = "SdkInfoApi")
public interface SdkInfoApi {
    @Operation(summary = "获取当前项目的硬盘根目录")
    public ResponseEntity<SdkStringResponseDto> rootPath();

    @Operation(summary = "检查项目错误")
    public ResponseEntity<SdkListResponseDto> checkErr();
}
