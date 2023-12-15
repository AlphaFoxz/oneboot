package com.github.alphafoxz.oneboot.sdk.controller;

import com.github.alphafoxz.oneboot.sdk.gen.restful.apis.SdkVersionApi;
import com.github.alphafoxz.oneboot.sdk.gen.restful.dtos.SdkVersionCheckResponse;
import com.github.alphafoxz.oneboot.sdk.service.version.SdkVersionCheckService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SdkVersionController implements SdkVersionApi {
    @Resource
    private SdkVersionCheckService sdkVersionCheckService;

    @Override
    public ResponseEntity<SdkVersionCheckResponse> getRestfulTemplateHash() {
        SdkVersionCheckResponse sdkVersionCheckResponse = sdkVersionCheckService.getRestfulTemplateHash(null);
        return ResponseEntity.ok(sdkVersionCheckResponse);
    }

    @Override
    public ResponseEntity<SdkVersionCheckResponse> checkRestfulJava() {
        SdkVersionCheckResponse sdkVersionCheckResponse = sdkVersionCheckService.checkRestfulJava(null);
        return ResponseEntity.ok(sdkVersionCheckResponse);
    }
}
