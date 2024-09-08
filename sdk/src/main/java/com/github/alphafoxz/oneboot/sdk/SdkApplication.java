package com.github.alphafoxz.oneboot.sdk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {
                "com.github.alphafoxz.oneboot.*.configuration",
                "com.github.alphafoxz.oneboot.*.service",
                "com.github.alphafoxz.oneboot.*.controller",
                "com.github.alphafoxz.oneboot.*.gen",
                "com.github.alphafoxz.oneboot.*.domain",
        }
)
public class SdkApplication {
    public static void main(String[] args) {
        SpringApplication.run(SdkApplication.class, args);
    }
}
