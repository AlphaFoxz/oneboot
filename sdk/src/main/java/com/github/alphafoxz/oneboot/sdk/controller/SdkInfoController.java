package com.github.alphafoxz.oneboot.sdk.controller;

import com.github.alphafoxz.oneboot.sdk.SdkConstants;
import com.github.alphafoxz.oneboot.sdk.service.SdkInfoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "SdkInfoController", description = "Sdk模块基本信息接口")
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
