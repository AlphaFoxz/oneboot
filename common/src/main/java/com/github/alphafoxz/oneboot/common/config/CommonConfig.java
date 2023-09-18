package com.github.alphafoxz.oneboot.common.config;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig {
    @Resource
    private CommonProperties commonProperties;

    public String getModuleName() {
        return commonProperties.getModuleName();
    }

    public String getBasePackage() {
        return commonProperties.getBasePackage();
    }

    public String getPackage() {
        return commonProperties.getBasePackage() + "." + commonProperties.getModuleName();
    }
}
