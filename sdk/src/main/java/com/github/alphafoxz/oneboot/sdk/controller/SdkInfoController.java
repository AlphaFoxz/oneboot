package com.github.alphafoxz.oneboot.sdk.controller;

import com.github.alphafoxz.oneboot.sdk.SdkConstants;
import com.github.alphafoxz.oneboot.sdk.service.SdkInfoService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/_sdk/info")
public class SdkInfoController {
    @Resource
    private SdkInfoService sdkInfoService;

    @GetMapping("/rootPath")
    public ResponseEntity<?> rootPath() {
        return ResponseEntity.ok(SdkConstants.PROJECT_ROOT_PATH);
    }

    @GetMapping("/checkErr")
    public ResponseEntity<?> checkErr() {
        return ResponseEntity.ok(sdkInfoService.checkThriftErr());
    }
}
