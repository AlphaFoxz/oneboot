package com.github.alphafoxz.oneboot.sdk.controller;

import cn.hutool.core.lang.Snowflake;
import com.github.alphafoxz.oneboot.sdk.gen.restful.apis.SdkThriftApi;
import com.github.alphafoxz.oneboot.sdk.gen.restful.dtos.SdkCodeTemplateResponseDto;
import com.github.alphafoxz.oneboot.sdk.gen.restful.dtos.SdkLongResponseDto;
import com.github.alphafoxz.oneboot.sdk.gen.restful.dtos.SdkStringResponseDto;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkStringRequestDto;
import com.github.alphafoxz.oneboot.sdk.service.SdkThriftService;
import com.github.alphafoxz.oneboot.sdk.service.common.SdkRestfulConvertor;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SdkThriftController implements SdkThriftApi {
    @Resource
    private SdkThriftService sdkThriftService;
    @Resource
    private Snowflake snowflake;

    @Override
    public ResponseEntity<SdkLongResponseDto> getServerPort() {
        return ResponseEntity.ok(sdkThriftService.getServerPort());
    }

    @Override
    public ResponseEntity<SdkStringResponseDto> getExecutableFilePath() {
        return ResponseEntity.ok(SdkRestfulConvertor.INSTANCE.fromThriftSdkStringResponseDto(sdkThriftService.getExecutableFilePath()));
    }

    @Override
    public ResponseEntity<SdkCodeTemplateResponseDto> getTemplateContentByPath(String filePath) {
        SdkStringRequestDto param = new SdkStringRequestDto(snowflake.nextId(), snowflake.nextId(), filePath);
        return ResponseEntity.ok(SdkRestfulConvertor.INSTANCE.fromThriftSdkCodeTemplateResponseDto(sdkThriftService.getTemplateContentByPath(param)));
    }
}
