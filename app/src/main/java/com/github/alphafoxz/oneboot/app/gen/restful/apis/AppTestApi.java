package com.github.alphafoxz.oneboot.app.gen.restful.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import com.github.alphafoxz.oneboot.common.standard.framework.HttpController;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import com.github.alphafoxz.oneboot.preset_sys.service.framework.PageResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.github.alphafoxz.oneboot.app.gen.restful.dtos.AppTestInfoDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
            @Parameter(description = "主键") @PathVariable("id") Long id
    );

    @GetMapping({"/queryPage/{pageNum}/{pageSize}"})
    @Operation(summary = "分页", responses = {
            @ApiResponse(description = "请求成功", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(description = "无权限", responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "参数无效", responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
    })
    public ResponseEntity<PageResponse<AppTestInfoDto>> queryPage(
            @Parameter(description = "页码") @PathVariable("pageNum") Integer pageNum,
            @Parameter(description = "每页数据量") @PathVariable("pageSize") Integer pageSize
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