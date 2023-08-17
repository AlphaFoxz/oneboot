package com.github.alphafoxz.oneboot.sdk.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/_sdk/command")
public class SdkCommandController {
    @PostMapping("/execute")
    public ResponseEntity<?> execute(@RequestAttribute("command") String command) {
        return ResponseEntity.ok(command);
    }
}
