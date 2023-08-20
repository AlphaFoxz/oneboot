package com.github.alphafoxz.oneboot.sdk.controller;

import cn.hutool.core.lang.Snowflake;
import com.github.alphafoxz.oneboot.sdk.gen.restful.apis.SdkGenCodeApi;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkListResponseDto;
import com.github.alphafoxz.oneboot.sdk.service.SdkGenCodeService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/_sdk/genCode")
public class SdkGenCodeController implements SdkGenCodeApi {
    @Resource
    private SdkGenCodeService sdkGenCodeService;
    @Resource
    private Snowflake snowflake;

    @GetMapping("/generateJavaRpc")
    public ResponseEntity<SdkListResponseDto> generateJavaRpc() {
        return ResponseEntity.ok(sdkGenCodeService.generateJavaRpc(snowflake.nextId()));
    }
}
