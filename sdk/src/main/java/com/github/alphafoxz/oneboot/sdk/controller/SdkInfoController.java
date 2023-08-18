package com.github.alphafoxz.oneboot.sdk.controller;

import com.github.alphafoxz.oneboot.common.toolkit.coding.CollUtil;
import com.github.alphafoxz.oneboot.sdk.SdkConstants;
import com.github.alphafoxz.oneboot.sdk.service.SdkInfoService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        List<String> err = CollUtil.newArrayList();
        List<String> checkThriftErr = sdkInfoService.checkThriftErr();
        err.addAll(checkThriftErr);
        return ResponseEntity.ok(err);
    }
}
