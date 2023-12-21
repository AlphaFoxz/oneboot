package com.github.alphafoxz.oneboot.preset_sys.gen.restful.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import com.github.alphafoxz.oneboot.common.standard.framework.HttpController;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysAuthRoleInfoDto;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysAuthLoginParam;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysAuthTokenInfoDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.domain.Page;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysAuthTokenResponse;

@RequestMapping({"/preset_sys/auth"})
@Tag(name = "PsysAuthApi", description = "登录接口")
public interface PsysAuthApi extends HttpController {
    @PostMapping({"/login"})
    @Operation(summary = "登录接口，返回token", responses = {
            @ApiResponse(description = "请求成功", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(description = "无权限", responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "参数无效", responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
    })
    public ResponseEntity<PsysAuthTokenResponse> login(
            @Parameter(description = "用户名") @RequestBody PsysAuthLoginParam loginParam
    );

    @GetMapping({"/logout"})
    @Operation(summary = "退出登录", responses = {
            @ApiResponse(description = "请求成功", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(description = "无权限", responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "参数无效", responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
    })
    public ResponseEntity<?> logout();

    @PostMapping({"/refresh"})
    @Operation(summary = "传入旧token，如果其中的refreshToken没有过期则返回一对新的token", responses = {
            @ApiResponse(description = "请求成功", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(description = "无权限", responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "参数无效", responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
    })
    public ResponseEntity<PsysAuthTokenResponse> refresh(
            @Parameter(description = "旧的token") @RequestBody PsysAuthTokenInfoDto oldToken
    );

    @GetMapping({"/role/list/{pageNum}/{pageSize}"})
    @Operation(summary = "角色管理列表", responses = {
            @ApiResponse(description = "请求成功", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(description = "无权限", responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "参数无效", responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
    })
    public ResponseEntity<Page<PsysAuthRoleInfoDto>> roleList(
            @Parameter(description = "页码") @PathVariable Integer pageNum,
            @Parameter(description = "每页数据量") @PathVariable Integer pageSize
    );

    @GetMapping({"/user/list"})
    @Operation(summary = "用户管理列表", responses = {
            @ApiResponse(description = "请求成功", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(description = "无权限", responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "参数无效", responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
    })
    public ResponseEntity<Page<PsysAuthRoleInfoDto>> userList(
            @Parameter(description = "页码") Integer pageNum,
            @Parameter(description = "每页数据量") Integer pageSize
    );

}