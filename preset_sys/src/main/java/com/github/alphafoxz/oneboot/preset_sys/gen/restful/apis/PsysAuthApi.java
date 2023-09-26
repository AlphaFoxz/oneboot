package com.github.alphafoxz.oneboot.preset_sys.gen.restful.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import com.github.alphafoxz.oneboot.common.interfaces.framework.HttpController;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysLoginParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysUserTokenDto;

@RequestMapping({"/preset_sys/auth"})
@Tag(name = "PsysAuthApi", description = "登录接口")
public interface PsysAuthApi extends HttpController {
    @PostMapping({"/login"})
    @Operation(summary = "登录接口，返回token")
    public ResponseEntity<PsysUserTokenDto> login(
            @Parameter(description = "用户名") @RequestBody PsysLoginParam loginParam
    );

    @GetMapping({"/logout"})
    @Operation(summary = "退出登录")
    public ResponseEntity<?> logout();

    @PostMapping({"/refresh"})
    @Operation(summary = "传入旧token，如果其中的refreshToken没有过期则返回一对新的token")
    public ResponseEntity<PsysUserTokenDto> refresh(
            @Parameter(description = "旧的token") @RequestBody PsysUserTokenDto oldToken
    );

}