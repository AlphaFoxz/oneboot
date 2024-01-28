package com.github.alphafoxz.oneboot.sdk.controller;

import cn.hutool.core.lang.Snowflake;
import com.github.alphafoxz.oneboot.sdk.gen.restful.apis.SdkGenCodeApi;
import com.github.alphafoxz.oneboot.sdk.service.gen.SdkGenCrudService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SdkGenCodeController implements SdkGenCodeApi {
    @Resource
    private SdkGenCrudService sdkGenCrudService;

    @Override
    public ResponseEntity<?> generateTableCrud(String moduleName, String poName, Integer serviceType, Boolean force) {
        sdkGenCrudService.generateTableCrud(moduleName, poName, serviceType, force);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<?> generateModuleCrud(String moduleName, Integer serviceType, Boolean force) {
        sdkGenCrudService.generateModuleCrud(moduleName, serviceType, force);
        return ResponseEntity.ok(null);
    }
}
