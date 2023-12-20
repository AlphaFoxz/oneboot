package com.github.alphafoxz.oneboot.common.configuration;

import com.github.alphafoxz.oneboot.common.standard.OnebootModuleConfig;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfiguration implements OnebootModuleConfig {
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
