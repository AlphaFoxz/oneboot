package com.github.alphafoxz.oneboot.preset_sys.gen.restl.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import com.github.alphafoxz.oneboot.core.standard.service.HttpController;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.github.alphafoxz.oneboot.preset_sys.service.framework.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.lang.Nullable;
import com.github.alphafoxz.oneboot.preset_sys.gen.restl.dtos.PsysAbacDynamicAuthDto;

@RequestMapping({"/preset_sys/abacDynamic"})
@Tag(name = "PsysAbacDynamicApi", description = "ABAC动态授权接口")
public interface PsysAbacDynamicApi extends HttpController {
    @GetMapping(value = {"/query"})
    @Operation(summary = "查询分页", responses = {
            @ApiResponse(description = "请求成功", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(description = "无权限", responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "参数无效", responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
    })
    public ResponseEntity<Page<PsysAbacDynamicAuthDto>> query(
            @Parameter(description = "") @Nullable @RequestParam Integer pageNum,
            @Parameter(description = "") @Nullable @RequestParam Integer pageSize
    );

    @PostMapping(value = {"/save"})
    @Operation(summary = "创建", responses = {
            @ApiResponse(description = "请求成功", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(description = "无权限", responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "参数无效", responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
    })
    public ResponseEntity<Boolean> save(
            @Parameter(description = "") @RequestBody PsysAbacDynamicAuthDto dto
    );

    @GetMapping(value = {"/delete"})
    @Operation(summary = "根据id删除", responses = {
            @ApiResponse(description = "请求成功", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(description = "无权限", responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "参数无效", responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
    })
    public ResponseEntity<Boolean> delete(
            @Parameter(description = "") @RequestParam String id
    );

}