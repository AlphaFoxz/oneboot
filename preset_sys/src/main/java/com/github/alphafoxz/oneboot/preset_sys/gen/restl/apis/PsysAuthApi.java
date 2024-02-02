package com.github.alphafoxz.oneboot.preset_sys.gen.restl.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import com.github.alphafoxz.oneboot.core.standard.framework.HttpController;
import org.springframework.web.bind.annotation.RequestBody;
import com.github.alphafoxz.oneboot.preset_sys.gen.restl.dtos.PsysAuthTokenResponse;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import com.github.alphafoxz.oneboot.preset_sys.gen.restl.dtos.PsysAuthRouteDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.lang.Nullable;
import com.github.alphafoxz.oneboot.preset_sys.gen.restl.dtos.PsysAuthTokenInfoDto;

@RequestMapping({"/preset_sys/auth"})
@Tag(name = "PsysAuthApi", description = "授权接口")
public interface PsysAuthApi extends HttpController {
    // PsysAuthDto.PsysAuthTokenResponse login(/*用户名*/ PsysAuthParam.PsysAuthLoginParam loginParam)
    @PostMapping(value = {"/login"})
    @Operation(summary = "登录接口，返回token", responses = {
            @ApiResponse(description = "请求成功", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(description = "无权限", responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "参数无效", responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
    })
    public default ResponseEntity<PsysAuthTokenResponse> login(
        @RequestBody java.util.Map<String, Object> _requestMap
    ) {
        return login((String) _requestMap.get("username"), (String) _requestMap.get("password"));
    }

    public ResponseEntity<PsysAuthTokenResponse> login(
            @Nullable String username,
            @Nullable String password
    );

    @GetMapping(value = {"/logout"})
    @Operation(summary = "退出登录", responses = {
            @ApiResponse(description = "请求成功", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(description = "无权限", responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "参数无效", responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
    })
    public ResponseEntity<?> logout();

    @PostMapping(value = {"/refreshToken"})
    @Operation(summary = "传入旧token，如果其中的refreshToken没有过期则返回一对新的token", responses = {
            @ApiResponse(description = "请求成功", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(description = "无权限", responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "参数无效", responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
    })
    public ResponseEntity<PsysAuthTokenResponse> refreshToken(
            @Parameter(description = "旧的token") @RequestBody PsysAuthTokenInfoDto oldToken
    );

    @PostMapping(value = {"/queryRoute"})
    @Operation(summary = "获取路由", responses = {
            @ApiResponse(description = "请求成功", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(description = "无权限", responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "参数无效", responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
    })
    public ResponseEntity<List<PsysAuthRouteDto>> queryRoute();

}