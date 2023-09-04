package com.github.alphafoxz.oneboot.app.gen.restful.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Parameter;
import com.github.alphafoxz.oneboot.app.gen.restful.dtos.TestResponseDto;
import com.github.alphafoxz.oneboot.app.gen.restful.dtos.TestEditParamDto;

// 本api为自动生成代码，请勿修改
@Tag(name = "TestApi", description = "测试API")
public interface TestApi {
    @Operation(summary = "查询请求")
    public ResponseEntity<TestResponseDto> query(
            @Parameter(description = "") TestEditParamDto editParam
    );

    // 此处应该进行测试
    // 看看能否实现对文件的下载
    @Operation(summary = "下载")
    public ResponseEntity<?> download(
            @Parameter(description = "文件路径") String url
    );

}