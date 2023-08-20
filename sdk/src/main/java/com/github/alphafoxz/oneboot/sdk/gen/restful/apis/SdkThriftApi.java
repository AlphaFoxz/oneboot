package com.github.alphafoxz.oneboot.sdk.gen.restful.apis;

import com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkLongResponseDto;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkStringResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "thrift功能接口（rpc跨语言通信）", description = "SdkThriftApi")
public interface SdkThriftApi {
    public ResponseEntity<SdkLongResponseDto> getServerPort();

    public ResponseEntity<SdkStringResponseDto> getExecutableFilePath();
}
