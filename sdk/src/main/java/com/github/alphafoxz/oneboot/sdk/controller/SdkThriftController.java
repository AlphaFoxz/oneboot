package com.github.alphafoxz.oneboot.sdk.controller;

import com.github.alphafoxz.oneboot.sdk.gen.thrift.structs.SdkListResponseStruct;
import com.github.alphafoxz.oneboot.sdk.service.SdkThriftService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "SdkThriftController", description = "thrift功能接口（rpc跨语言通信）")
@RestController
@RequestMapping("/_sdk/thrift")
public class SdkThriftController {
    @Resource
    private SdkThriftService sdkThriftService;

    @GetMapping("/generateAll")
    public ResponseEntity<?> generateAll() {
        SdkListResponseStruct result = sdkThriftService.generateAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getServerPort")
    public ResponseEntity<?> getServerPort() {
        return ResponseEntity.ok(sdkThriftService.getServerPort());
    }

    @GetMapping("/getExecutableFilePath")
    public ResponseEntity<?> getExecutableFilePath() {
        return ResponseEntity.ok(sdkThriftService.getExecutableFilePath());
    }
}
