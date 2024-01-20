package com.github.alphafoxz.oneboot.sdk.controller;

import cn.hutool.core.lang.Snowflake;
import com.github.alphafoxz.oneboot.sdk.gen.restful.apis.SdkGenCodeApi;
import com.github.alphafoxz.oneboot.sdk.gen.restful.dtos.SdkListResponseDto;
import com.github.alphafoxz.oneboot.sdk.service.common.SdkRestfulConvert;
import com.github.alphafoxz.oneboot.sdk.service.gen.SdkGenCrudService;
import com.github.alphafoxz.oneboot.sdk.service.gen.RestfulDslGenCodeService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SdkGenCodeController implements SdkGenCodeApi {
    @Resource
    private RestfulDslGenCodeService restfulDslGenCodeService;
    @Resource
    private SdkGenCrudService sdkGenCrudService;
    @Resource
    private Snowflake snowflake;

    @Override
    public ResponseEntity<SdkListResponseDto> generateJavaRpc() {
        return ResponseEntity.ok(SdkRestfulConvert.INSTANCE.fromThriftSdkListResponseDto(restfulDslGenCodeService.generateJavaRpc(snowflake.nextId())));
    }

    @Override
    public ResponseEntity<?> generateTableCrud(String moduleName, String poName, int serviceType, Boolean force) {
        sdkGenCrudService.generateTableCrud(moduleName, poName, serviceType, force);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<?> generateModuleCrud(String moduleName, int serviceType, Boolean force) {
        sdkGenCrudService.generateModuleCrud(moduleName, serviceType, force);
        return ResponseEntity.ok(null);
    }

}
