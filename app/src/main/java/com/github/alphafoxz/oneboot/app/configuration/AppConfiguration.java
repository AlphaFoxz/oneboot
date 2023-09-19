package com.github.alphafoxz.oneboot.app.configuration;

import com.github.alphafoxz.oneboot.common.interfaces.OnebootModuleConfig;
import com.github.alphafoxz.oneboot.common.configuration.CommonProperties;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration implements OnebootModuleConfig {
    @Resource
    private AppProperties appProperties;
    @Resource
    private CommonProperties commonProperties;

    @Override
    public String getModuleName() {
        return appProperties.getModuleName();
    }

    @Override
    public String getPackage() {
        return commonProperties.getBasePackage() + "." + appProperties.getModuleName();
    }
}
