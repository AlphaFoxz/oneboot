package com.github.alphafoxz.oneboot.preset_sys.controller;

import com.github.alphafoxz.oneboot.preset_sys.gen.restful.apis.PsysLoginApi;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysLoginUserPasswordParam;
import com.github.alphafoxz.oneboot.preset_sys.service.human_resources.PsysLoginService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class PsysLoginController implements PsysLoginApi {
    @Resource
    private PsysLoginService psysLoginService;

    @Override
    public ResponseEntity<String> token(PsysLoginUserPasswordParam loginParam) {
        String token = psysLoginService.queryOrGenToken(loginParam.getUsername(), loginParam.getPassword());
        return ResponseEntity.ok(token);
    }
}
