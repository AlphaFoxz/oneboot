package com.github.alphafoxz.oneboot.app.config;

import com.github.alphafoxz.oneboot.common.Iface.OnebootModuleConfig;
import com.github.alphafoxz.oneboot.common.config.CommonProperties;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig implements OnebootModuleConfig {
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
