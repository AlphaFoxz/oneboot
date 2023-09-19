package com.github.alphafoxz.oneboot.preset_sys.gen.restful.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysLoginUserPasswordParam;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping({"/preset_sys/login"})
@Tag(name = "PsysLoginApi", description = "登录接口")
public interface PsysLoginApi {
    @PostMapping({"/token"})
    @Operation(summary = "登录接口，返回token")
    public ResponseEntity<String> token(
            @Parameter(description = "用户名") @RequestBody  PsysLoginUserPasswordParam loginParam
    );

}