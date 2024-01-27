package com.github.alphafoxz.oneboot.preset_sys.gen.restful.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import com.github.alphafoxz.oneboot.core.standard.framework.HttpController;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.RequestParam;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysAccountUserPageParam;
import com.github.alphafoxz.oneboot.preset_sys.service.framework.Page;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysAccountDepartmentInfoDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysAccountRolePageParam;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysAccountRoleInfoDto;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysAccountUserInfoDto;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysAccountDepartmentPageParam;

@RequestMapping({"/preset_sys/account"})
@Tag(name = "PsysAccountApi", description = "账号接口")
public interface PsysAccountApi extends HttpController {
    @GetMapping(value = {"/role/page"})
    @Operation(summary = "角色管理列表", responses = {
            @ApiResponse(description = "请求成功", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(description = "无权限", responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "参数无效", responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
    })
    public ResponseEntity<Page<PsysAccountRoleInfoDto>> rolePage(
            @Parameter(description = "角色分页查询传参") @RequestParam PsysAccountRolePageParam param
    );

    @GetMapping(value = {"/user/page"})
    @Operation(summary = "用户管理列表", responses = {
            @ApiResponse(description = "请求成功", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(description = "无权限", responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "参数无效", responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
    })
    public ResponseEntity<Page<PsysAccountUserInfoDto>> userPage(
            @Parameter(description = "用户分页查询传参") @RequestParam PsysAccountUserPageParam param
    );

    @GetMapping(value = {"/department/page"})
    @Operation(summary = "组织机构列表", responses = {
            @ApiResponse(description = "请求成功", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(description = "无权限", responseCode = "403", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(description = "参数无效", responseCode = "400", content = @Content(schema = @Schema(hidden = true))),
    })
    public ResponseEntity<Page<PsysAccountDepartmentInfoDto>> departmentPage(
            @Parameter(description = "组织机构分页查询传参") @RequestParam PsysAccountDepartmentPageParam param
    );

}