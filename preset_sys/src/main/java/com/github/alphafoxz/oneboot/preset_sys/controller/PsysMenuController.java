package com.github.alphafoxz.oneboot.preset_sys.controller;

import com.github.alphafoxz.oneboot.preset_sys.gen.restl.apis.PsysMenuApi;
import com.github.alphafoxz.oneboot.preset_sys.gen.restl.dtos.PsysMenuResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PsysMenuController implements PsysMenuApi {
    @Override
    public ResponseEntity<PsysMenuResponseDto> getAsyncRoutes(String token) {
        return null;
    }
}
