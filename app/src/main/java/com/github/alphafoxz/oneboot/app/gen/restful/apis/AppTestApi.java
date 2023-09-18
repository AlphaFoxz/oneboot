package com.github.alphafoxz.oneboot.app.gen.restful.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.github.alphafoxz.oneboot.app.gen.restful.dtos.AppTestInfoDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// 本api为自动生成代码，请勿修改
@RequestMapping({"/app/test"})
@Tag(name = "AppTestApi", description = "测试API")
public interface AppTestApi {
    @GetMapping({"/query/{id}"})
    @Operation(summary = "查询单条")
    public ResponseEntity<AppTestInfoDto> queryOne(
            @Parameter(description = "主键") @PathVariable Long id
    );

    @GetMapping({"/queryPage/{pageNum}/{pageSize}"})
    @Operation(summary = "查询单条")
    public ResponseEntity<AppTestInfoDto> queryPage(
            @Parameter(description = "页码") @PathVariable Integer pageNum,
            @Parameter(description = "每页数据量") @PathVariable Integer pageSize
    );

    @PostMapping({"/update"})
    @Operation(summary = "更新")
    public ResponseEntity<Boolean> update(
            @Parameter(description = "更新参数") @RequestBody  AppTestInfoDto param
    );

}