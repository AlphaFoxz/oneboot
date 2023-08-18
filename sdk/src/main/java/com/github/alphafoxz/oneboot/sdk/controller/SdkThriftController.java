package com.github.alphafoxz.oneboot.sdk.controller;

import com.github.alphafoxz.oneboot.sdk.service.SdkThriftService;
import com.github.alphafoxz.oneboot.sdk.thrift.structs.SdkListResponseStruct;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
