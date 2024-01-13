package com.github.alphafoxz.oneboot.preset_sys.controller;

import com.github.alphafoxz.oneboot.preset_sys.gen.restful.apis.PsysAuthApi;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysAuthRouteDto;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysAuthTokenInfoDto;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysAuthTokenResponse;
import com.github.alphafoxz.oneboot.preset_sys.service.auth.PsysAuthTokenService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PsysAuthController implements PsysAuthApi {
    @Resource
    private PsysAuthTokenService psysAuthTokenService;

    @Override
    public ResponseEntity<PsysAuthTokenResponse> login(String username, String password) {
        PsysAuthTokenResponse result = new PsysAuthTokenResponse();
        result.setSuccess(true);
        PsysAuthTokenInfoDto token = psysAuthTokenService.getAccessTokenAndRefreshToken(username, password);
        result.setData(token);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<?> logout() {
        psysAuthTokenService.logout();
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<PsysAuthTokenResponse> refreshToken(PsysAuthTokenInfoDto userInfoDto) {
        PsysAuthTokenResponse result = new PsysAuthTokenResponse();
        result.setSuccess(true);
        PsysAuthTokenInfoDto token = psysAuthTokenService.refreshTokenByOld(userInfoDto);
        result.setData(token);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<List<PsysAuthRouteDto>> queryRoute() {
        //TODO
        return null;
    }
}
