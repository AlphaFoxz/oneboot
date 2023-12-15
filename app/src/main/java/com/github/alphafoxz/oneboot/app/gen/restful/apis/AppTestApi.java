package com.github.alphafoxz.oneboot.app.gen.restful.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import com.github.alphafoxz.oneboot.common.interfaces.framework.HttpController;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.github.alphafoxz.oneboot.app.gen.restful.dtos.AppTestInfoDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.domain.Page;

// 本api为自动生成代码，请勿修改
@RequestMapping({"/app/test"})
@Tag(name = "AppTestApi", description = "测试API")
public interface AppTestApi extends HttpController {
    @GetMapping({"/query/{id}"})
    @Operation(summary = "查询单条", responses = {
            @ApiResponse(description = "请求成功", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(description = "无权限", responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "参数无效", responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
    })
    public ResponseEntity<AppTestInfoDto> queryOne(
            @Parameter(description = "主键") @PathVariable Long id
    );

    @GetMapping({"/queryPage/{pageNum}/{pageSize}"})
    @Operation(summary = "分页", responses = {
            @ApiResponse(description = "请求成功", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(description = "无权限", responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "参数无效", responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
    })
    public ResponseEntity<Page<AppTestInfoDto>> queryPage(
            @Parameter(description = "页码") @PathVariable Integer pageNum,
            @Parameter(description = "每页数据量") @PathVariable Integer pageSize
    );

    @PostMapping({"/update"})
    @Operation(summary = "更新", responses = {
            @ApiResponse(description = "请求成功", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(description = "无权限", responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "参数无效", responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
    })
    public ResponseEntity<Boolean> update(
            @Parameter(description = "更新参数") @RequestBody AppTestInfoDto param
    );

}