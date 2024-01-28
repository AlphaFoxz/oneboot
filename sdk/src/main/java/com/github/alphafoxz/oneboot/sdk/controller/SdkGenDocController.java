package com.github.alphafoxz.oneboot.sdk.controller;

import com.github.alphafoxz.oneboot.core.toolkit.coding.FileUtil;
import com.github.alphafoxz.oneboot.sdk.gen.restful.apis.SdkGenDocApi;
import com.github.alphafoxz.oneboot.sdk.service.gen.SdkGenDocService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class SdkGenDocController implements SdkGenDocApi {
    @Resource
    private SdkGenDocService sdkGenDocService;

    @Override
    public ResponseEntity<byte[]> generateWordApi(String moduleName) {
        File file = sdkGenDocService.generateWordApi(moduleName);
        String fileName = moduleName + "模块Api文档.docx";
        return U.fileBodyBuilder(OK_200, fileName)
                .body(FileUtil.readBytes(file));
    }
}
