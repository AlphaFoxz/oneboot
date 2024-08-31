package com.github.alphafoxz.oneboot.sdk.controller;

import com.github.alphafoxz.oneboot.sdk.gen.restl.apis.SdkGenCodeApi;
import com.github.alphafoxz.oneboot.sdk.service.gen.SdkGenCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SdkGenCodeController implements SdkGenCodeApi {
    private final SdkGenCrudService sdkGenCrudService;

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
