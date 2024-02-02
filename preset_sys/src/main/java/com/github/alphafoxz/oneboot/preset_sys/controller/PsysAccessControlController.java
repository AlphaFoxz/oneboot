package com.github.alphafoxz.oneboot.preset_sys.controller;

import com.github.alphafoxz.oneboot.preset_sys.gen.restl.apis.PsysAccessControlApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class PsysAccessControlController implements PsysAccessControlApi {
    @Override
    public ResponseEntity<Set<String>> queryCurrentAttributes() {
        return null;
    }
}
