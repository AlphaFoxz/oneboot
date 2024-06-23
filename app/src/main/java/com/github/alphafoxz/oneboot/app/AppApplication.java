package com.github.alphafoxz.oneboot.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {
                "com.github.alphafoxz.oneboot.*.configuration",
                "com.github.alphafoxz.oneboot.*.service",
                "com.github.alphafoxz.oneboot.*.controller",
                "com.github.alphafoxz.oneboot.*.gen",
        }
)
public class AppApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

}
