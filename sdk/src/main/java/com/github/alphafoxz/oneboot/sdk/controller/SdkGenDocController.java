package com.github.alphafoxz.oneboot.sdk.controller;

import com.github.alphafoxz.oneboot.common.toolkit.coding.FileUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.URLUtil;
import com.github.alphafoxz.oneboot.sdk.gen.restful.apis.SdkGenDocApi;
import com.github.alphafoxz.oneboot.sdk.service.gen.SdkGenDocService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
        String fileName = URLUtil.encode(moduleName + "模块Api文档.docx");
        return ResponseEntity.status(OK_200)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(file.length())
                .header(HttpHeaders.PRAGMA, "no-cache")
                .header(HttpHeaders.EXPIRES, "0")
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .body(FileUtil.readBytes(file));
    }

    @Override
    public ResponseEntity<byte[]> generateExcelApi(String moduleName) {
        File file = sdkGenDocService.generateExcelApi("preset_sys");
        String fileName = URLUtil.encode(moduleName + "模块Api文档.xlsx");
        return ResponseEntity.status(OK_200)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(file.length())
                .header(HttpHeaders.PRAGMA, "no-cache")
                .header(HttpHeaders.EXPIRES, "0")
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .body(FileUtil.readBytes(file));
    }
}
