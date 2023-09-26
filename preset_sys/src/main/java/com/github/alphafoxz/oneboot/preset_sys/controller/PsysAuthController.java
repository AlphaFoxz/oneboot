package com.github.alphafoxz.oneboot.preset_sys.controller;

import com.github.alphafoxz.oneboot.common.toolkit.container.tuple.Tuple2;
import com.github.alphafoxz.oneboot.common.toolkit.container.tuple.Tuples;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.apis.PsysAuthApi;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysLoginParam;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysUserTokenDto;
import com.github.alphafoxz.oneboot.preset_sys.service.auth.PsysLoginService;
import jakarta.annotation.Resource;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@Setter
public class PsysAuthController implements PsysAuthApi {
    @Resource
    private PsysLoginService psysLoginService;

    public ResponseEntity<PsysUserTokenDto> login(PsysLoginParam loginParam) {
        Tuple2<String, String> token = psysLoginService.getAccessTokenAndRefreshToken(loginParam.getUsername(), loginParam.getPassword());
        PsysUserTokenDto result = new PsysUserTokenDto()
                .setAccessToken(token.getIndex0())
                .setRefreshToken(token.getIndex1());
        return ResponseEntity.ok(result);
    }

    public ResponseEntity<?> logout() {
        psysLoginService.logout();
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<PsysUserTokenDto> refresh(PsysUserTokenDto oldToken) {
        Tuple2<String, String> token = psysLoginService.refreshTokenByOld(Tuples.of(oldToken.getAccessToken(), oldToken.getRefreshToken()));
        PsysUserTokenDto result = new PsysUserTokenDto()
                .setAccessToken(token.getIndex0())
                .setRefreshToken(token.getIndex1());
        return ResponseEntity.ok(result);
    }
}
