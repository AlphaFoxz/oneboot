package com.github.alphafoxz.oneboot.sdk.controller;

import cn.hutool.core.lang.Snowflake;
import com.github.alphafoxz.oneboot.sdk.gen.restful.apis.SdkThriftApi;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkLongResponseDto;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkStringRequestDto;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkStringResponseDto;
import com.github.alphafoxz.oneboot.sdk.gen.thrift.dtos.SdkThriftTemplateResponseDto;
import com.github.alphafoxz.oneboot.sdk.service.SdkThriftService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/_sdk/thrift")
public class SdkThriftController implements SdkThriftApi {
    @Resource
    private SdkThriftService sdkThriftService;
    @Resource
    private Snowflake snowflake;

    @GetMapping("/getServerPort")
    public ResponseEntity<SdkLongResponseDto> getServerPort() {
        return ResponseEntity.ok(sdkThriftService.getServerPort());
    }

    @GetMapping("/getExecutableFilePath")
    public ResponseEntity<SdkStringResponseDto> getExecutableFilePath() {
        return ResponseEntity.ok(sdkThriftService.getExecutableFilePath());
    }

    @Override
    @GetMapping
    public ResponseEntity<SdkThriftTemplateResponseDto> getTemplateContentByPath(@RequestParam String filePath) {
        SdkStringRequestDto param = new SdkStringRequestDto(snowflake.nextId(), snowflake.nextId(), filePath);
        return ResponseEntity.ok(sdkThriftService.getTemplateContentByPath(param));
    }
}
