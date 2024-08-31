package com.github.alphafoxz.oneboot.sdk.controller;

import com.github.alphafoxz.oneboot.core.toolkit.coding.FileUtil;
import com.github.alphafoxz.oneboot.sdk.gen.restl.apis.SdkGenDocApi;
import com.github.alphafoxz.oneboot.sdk.service.gen.SdkGenDocService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@RequiredArgsConstructor
public class SdkGenDocController implements SdkGenDocApi {
    private final SdkGenDocService sdkGenDocService;

    @Override
    public ResponseEntity<byte[]> generateWordApi(String moduleName) {
        File file = sdkGenDocService.generateWordApi(moduleName);
        String fileName = moduleName + "模块Api文档.docx";
        return U.fileBodyBuilder(OK_200, fileName)
                .body(FileUtil.readBytes(file));
    }
}
