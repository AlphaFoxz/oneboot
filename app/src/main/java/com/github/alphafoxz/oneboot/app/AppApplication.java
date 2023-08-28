package com.github.alphafoxz.oneboot.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {
                "com.github.alphafoxz.oneboot.*.config",
                "com.github.alphafoxz.oneboot.*.service",
                "com.github.alphafoxz.oneboot.*.controller",
                "com.github.alphafoxz.oneboot.*.convert",
                "com.github.alphafoxz.oneboot.*.gen.convert",
        }
)
public class AppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

}
