package com.github.alphafoxz.oneboot.app.gen.restful.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.github.alphafoxz.oneboot.app.gen.restful.dtos.AppTestEditParamDto;
import com.github.alphafoxz.oneboot.app.gen.restful.dtos.AppTestResponseDto;

// 本api为自动生成代码，请勿修改
@RequestMapping({"/app/test"})
@Tag(name = "AppTestApi", description = "测试API")
public interface AppTestApi {
    @PostMapping({"/query"})
    @Operation(summary = "查询请求")
    public ResponseEntity<AppTestResponseDto> query(
            @Parameter(description = "") AppTestEditParamDto editParam
    );

    // 此处应该进行测试
    // 看看能否实现对文件的下载
    @PostMapping({"/download"})
    @Operation(summary = "下载")
    public ResponseEntity<?> download(
            @Parameter(description = "文件路径") String url
    );

}