package com.github.alphafoxz.oneboot.sdk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {
                "com.github.alphafoxz.oneboot.*.config",
                "com.github.alphafoxz.oneboot.*.service",
                "com.github.alphafoxz.oneboot.*.controller",
        }
)
public class SdkApplication {
    public static void main(String[] args) {
        SpringApplication.run(SdkApplication.class, args);
    }
}
