package com.github.alphafoxz.oneboot.sdk.gen.restful.apis;

import com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkListResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Sdk模块基本信息接口", description = "SdkGenCodeApi")
public interface SdkGenCodeApi {
    @Operation(summary = "创建Java rpc代码")
    public ResponseEntity<SdkListResponseDto> generateJavaRpc();
}
