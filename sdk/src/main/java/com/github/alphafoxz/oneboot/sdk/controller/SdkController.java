package com.github.alphafoxz.oneboot.sdk.controller;

import com.github.alphafoxz.oneboot.sdk.SdkConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sdk")
public class SdkController {
    @GetMapping("/rootPath")
    public ResponseEntity<?> rootPath() {
        return ResponseEntity.ok(SdkConstants.getProjectRootPath());
    }
}
