package com.github.alphafoxz.oneboot.app.gen.restl.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import com.github.alphafoxz.oneboot.core.standard.service.HttpController;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import com.github.alphafoxz.oneboot.preset_sys.service.framework.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestMapping;
import com.github.alphafoxz.oneboot.app.gen.restl.dtos.AppTestInfoDto;

// 本api为自动生成代码，请勿修改
@RequestMapping({"/app/test"})
@Tag(name = "AppTestApi", description = "测试API")
public interface AppTestApi extends HttpController {
    @GetMapping(value = {"/query/{id}"})
    @Operation(summary = "查询单条", responses = {
            @ApiResponse(description = "请求成功", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(description = "无权限", responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "参数无效", responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
    })
    public ResponseEntity<AppTestInfoDto> queryOne(
            @Parameter(description = "主键") @PathVariable("id") Long id
    );

    @GetMapping(value = {"/queryPage/{pageNum}/{pageSize}"})
    @Operation(summary = "分页", responses = {
            @ApiResponse(description = "请求成功", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(description = "无权限", responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "参数无效", responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
    })
    public ResponseEntity<Page<AppTestInfoDto>> queryPage(
            @Parameter(description = "页码") @PathVariable("pageNum") Integer pageNum,
            @Parameter(description = "每页数据量") @PathVariable("pageSize") Integer pageSize
    );

    @PostMapping(value = {"/update"})
    @Operation(summary = "更新", responses = {
            @ApiResponse(description = "请求成功", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(description = "无权限", responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "参数无效", responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
    })
    public ResponseEntity<Boolean> update(
            @Parameter(description = "更新参数") @RequestBody AppTestInfoDto param
    );

    @GetMapping(value = {"/download"})
    @Operation(summary = "测试下载功能", responses = {
            @ApiResponse(description = "请求成功", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(description = "无权限", responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "参数无效", responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
    })
    public ResponseEntity<byte[]> download(
            @Parameter(description = "模块名称") @RequestParam Long id
    );

    @PostMapping(value = {"/upload"}, consumes = "multipart/form-data")
    @Operation(summary = "测试上传功能", responses = {
            @ApiResponse(description = "请求成功", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(description = "无权限", responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "参数无效", responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
    })
    public ResponseEntity<?> upload(
            @Parameter(description = "上传文件") MultipartFile file,
            @Parameter(description = "名称") String name
    );

}