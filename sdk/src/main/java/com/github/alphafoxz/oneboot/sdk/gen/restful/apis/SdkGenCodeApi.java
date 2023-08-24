package com.github.alphafoxz.oneboot.sdk.gen.restful.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkListResponseDto;

@Tag(name = "SdkGenCodeApi", description = "Sdk模块代码生成接口")
public interface SdkGenCodeApi{
    @Operation(summary = "创建所有Java rpc代码")
    public ResponseEntity<SdkListResponseDto> generateJavaRpc();

}
