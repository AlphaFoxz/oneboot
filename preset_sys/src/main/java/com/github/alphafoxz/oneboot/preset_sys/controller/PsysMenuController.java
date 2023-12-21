package com.github.alphafoxz.oneboot.preset_sys.controller;

import com.github.alphafoxz.oneboot.preset_sys.gen.restful.apis.PsysMenuApi;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysMenuResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PsysMenuController implements PsysMenuApi {
    @Override
    public ResponseEntity<PsysMenuResponseDto> getAsyncRoutes(String token) {
        return null;
    }
}
