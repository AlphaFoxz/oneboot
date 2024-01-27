package com.github.alphafoxz.oneboot.sdk.gen.restful.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import com.github.alphafoxz.oneboot.core.standard.framework.HttpController;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.github.alphafoxz.oneboot.sdk.gen.restful.enums.SdkCrudServiceTypeEnum;

@RequestMapping({"/_sdk/genCode"})
@Tag(name = "SdkGenCodeApi", description = "Sdk模块代码生成接口")
public interface SdkGenCodeApi extends HttpController {
    /**
     * @param serviceType 枚举值
     * @see SdkCrudServiceTypeEnum
     */
    @GetMapping(value = {"/generateTableCrud"})
    @Operation(summary = "创建单表CRUD代码", responses = {
            @ApiResponse(description = "请求成功", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(description = "无权限", responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "参数无效", responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
    })
    public ResponseEntity<?> generateTableCrud(
            @Parameter(description = "模块名称") @RequestParam String moduleName,
            @Parameter(description = "表名") @RequestParam String poName,
            @Parameter(description = "生成类型") @RequestParam Integer serviceType,
            @Parameter(description = "是否覆盖已有代码") @RequestParam Boolean force
    );

    /**
     * @param serviceType 枚举值
     * @see SdkCrudServiceTypeEnum
     */
    @GetMapping(value = {"/generateModuleCrud"})
    @Operation(summary = "创建整个模块的CRUD代码", responses = {
            @ApiResponse(description = "请求成功", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(description = "无权限", responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "参数无效", responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
    })
    public ResponseEntity<?> generateModuleCrud(
            @Parameter(description = "模块名称") @RequestParam String moduleName,
            @Parameter(description = "生成类型") @RequestParam Integer serviceType,
            @Parameter(description = "是否覆盖已有代码") @RequestParam Boolean force
    );

}