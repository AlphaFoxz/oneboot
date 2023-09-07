package com.github.alphafoxz.oneboot.sdk.controller;

import cn.hutool.core.lang.Snowflake;
import com.github.alphafoxz.oneboot.sdk.gen.restful.apis.SdkGenCodeApi;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkListResponseDto;
import com.github.alphafoxz.oneboot.sdk.service.SdkGenCodeService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SdkGenCodeController implements SdkGenCodeApi {
    @Resource
    private SdkGenCodeService sdkGenCodeService;
    @Resource
    private Snowflake snowflake;

    @Override
    public ResponseEntity<SdkListResponseDto> generateJavaRpc() {
        return ResponseEntity.ok(sdkGenCodeService.generateJavaRpc(snowflake.nextId()));
    }
}
