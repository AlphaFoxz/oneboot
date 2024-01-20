package com.github.alphafoxz.oneboot.sdk.controller;

import com.github.alphafoxz.oneboot.sdk.gen.restful.apis.RestfulDslApi;
import com.github.alphafoxz.oneboot.sdk.gen.restful.dtos.*;
import com.github.alphafoxz.oneboot.sdk.service.RestfulDslInfoService;
import com.github.alphafoxz.oneboot.sdk.service.gen.RestfulDslGenCodeService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestfulDslController implements RestfulDslApi {
    @Resource
    private RestfulDslGenCodeService restfulDslGenCodeService;
    @Resource
    private RestfulDslInfoService restfulDslInfoService;

    @Override
    public ResponseEntity<SdkMapResponseDto> generateTsClientApi(SdkCodeTemplateRequestDto templateDto, String genDir) {
        return ResponseEntity.ok(restfulDslGenCodeService.previewGenerateTsApi(templateDto, genDir));
    }

    @Override
    public ResponseEntity<SdkMapResponseDto> generateRustClientApi(SdkCodeTemplateRequestDto templateDto, String genDir) {
        return ResponseEntity.ok(restfulDslGenCodeService.previewGenerateRustApi(templateDto, genDir));
    }

    @Override
    public ResponseEntity<SdkListResponseDto> generateJavaServerApi(SdkCodeTemplateRequestDto templateDto) {
        return ResponseEntity.ok(restfulDslGenCodeService.generateJavaApi(templateDto));
    }

    @Override
    public ResponseEntity<SdkMapResponseDto> generateSql(SdkCodeTemplateRequestDto templateDto) {
        return ResponseEntity.ok(restfulDslGenCodeService.previewGenerateSql(templateDto));
    }

    @Override
    public ResponseEntity<SdkStringResponseDto> createOrUpdateFile(String filePath, String content) {
        return ResponseEntity.ok(restfulDslInfoService.createOrUpdateFile(filePath, content));
    }

    @Override
    public ResponseEntity<SdkStringResponseDto> createFolder(String folderPath) {
        return ResponseEntity.ok(restfulDslInfoService.createFolder(folderPath));
    }

    @Override
    public ResponseEntity<SdkStringResponseDto> renameFile(String filePath, String newPath) {
        return ResponseEntity.ok(restfulDslInfoService.renameFile(filePath, newPath));
    }

    @Override
    public ResponseEntity<SdkListResponseDto> deleteFile(String filePath) {
        return ResponseEntity.ok(restfulDslInfoService.deleteFile(filePath));
    }

    @Override
    public ResponseEntity<SdkCodeTemplateResponseDto> getTemplateContentByPath(String filePath) {
        return ResponseEntity.ok(restfulDslInfoService.getTemplateContentByPath(filePath));
    }

    @Override
    public ResponseEntity<SdkFileTreeResponseDto> getRestfulTemplateFileTree() {
        return ResponseEntity.ok(restfulDslInfoService.getRestfulTemplateFileTree());
    }

    @Override
    public ResponseEntity<SdkCodeTemplateResponseDto> getTemplateContentByImportPath(String temp_path, String import_path) {
        return ResponseEntity.ok(restfulDslInfoService.getTemplateContentByImportPath(temp_path, import_path));
    }
}
