package com.github.alphafoxz.oneboot.common.config;

import com.github.alphafoxz.oneboot.common.ifaces.OnebootModuleConfig;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig implements OnebootModuleConfig {
    @Resource
    private CommonProperties commonProperties;

    @Override
    public String getModuleName() {
        return commonProperties.getModuleName();
    }

    public String getBasePackage() {
        return commonProperties.getBasePackage();
    }

    @Override
    public String getPackage() {
        return commonProperties.getBasePackage() + "." + commonProperties.getModuleName();
    }
}
