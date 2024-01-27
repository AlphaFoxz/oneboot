package com.github.alphafoxz.oneboot.preset_sys.gen.restful.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import com.github.alphafoxz.oneboot.core.standard.framework.HttpController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysMenuResponseDto;

@RequestMapping({"/psys/menu"})
@Tag(name = "PsysMenuApi", description = "菜单接口")
public interface PsysMenuApi extends HttpController {
    @PostMapping(value = {"/getAsyncRoutes"})
    @Operation(summary = "根据Token动态获取菜单", responses = {
            @ApiResponse(description = "请求成功", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(description = "无权限", responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "参数无效", responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
    })
    public default ResponseEntity<PsysMenuResponseDto> getAsyncRoutes(
        @RequestBody java.util.Map<String, Object> _requestMap
    ) {
        return getAsyncRoutes((String) _requestMap.get("token"));
    }
    public ResponseEntity<PsysMenuResponseDto> getAsyncRoutes(
            String token
    );

}