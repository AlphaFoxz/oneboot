package com.github.alphafoxz.oneboot.domain.preset_sys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.github.alphafoxz.oneboot",
})
public class TestDomainApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestDomainApplication.class, args);
    }
}
