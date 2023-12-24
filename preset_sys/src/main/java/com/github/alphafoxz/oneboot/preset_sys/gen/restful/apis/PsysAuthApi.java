package com.github.alphafoxz.oneboot.preset_sys.gen.restful.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import com.github.alphafoxz.oneboot.common.standard.framework.HttpController;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysAuthUserPageParam;
import org.springframework.web.bind.annotation.PostMapping;
import com.github.alphafoxz.oneboot.preset_sys.service.framework.PageResponse;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysAuthRolePageParam;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysAuthRoleInfoDto;
import org.springframework.web.bind.annotation.RequestMapping;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysAuthTokenResponse;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.Parameter;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysAuthUserInfoDto;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysAuthLoginParam;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysAuthTokenInfoDto;
import org.springframework.web.bind.annotation.GetMapping;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysAuthDepartmentPageParam;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysDepartmentInfoDto;

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

    @PostMapping({"/refreshToken"})
    @Operation(summary = "传入旧token，如果其中的refreshToken没有过期则返回一对新的token", responses = {
            @ApiResponse(description = "请求成功", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(description = "无权限", responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "参数无效", responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
    })
    public ResponseEntity<PsysAuthTokenResponse> refreshToken(
            @Parameter(description = "旧的token") @RequestBody PsysAuthTokenInfoDto oldToken
    );

    @GetMapping({"/role/page"})
    @Operation(summary = "角色管理列表", responses = {
            @ApiResponse(description = "请求成功", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(description = "无权限", responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "参数无效", responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
    })
    public ResponseEntity<PageResponse<PsysAuthRoleInfoDto>> rolePage(
            @Parameter(description = "角色分页查询传参") PsysAuthRolePageParam param
    );

    @GetMapping({"/user/page"})
    @Operation(summary = "用户管理列表", responses = {
            @ApiResponse(description = "请求成功", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(description = "无权限", responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "参数无效", responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
    })
    public ResponseEntity<PageResponse<PsysAuthUserInfoDto>> userPage(
            @Parameter(description = "用户分页查询传参") PsysAuthUserPageParam param
    );

    @GetMapping({"/department/page"})
    @Operation(summary = "组织机构列表", responses = {
            @ApiResponse(description = "请求成功", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(description = "无权限", responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "参数无效", responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
    })
    public ResponseEntity<PageResponse<PsysDepartmentInfoDto>> departmentPage(
            @Parameter(description = "组织机构分页查询传参") PsysAuthDepartmentPageParam param
    );

}