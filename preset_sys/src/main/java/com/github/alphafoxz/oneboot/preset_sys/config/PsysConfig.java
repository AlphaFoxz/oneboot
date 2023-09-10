package com.github.alphafoxz.oneboot.preset_sys.config;

import com.github.alphafoxz.oneboot.common.ifaces.OnebootModuleConfig;
import com.github.alphafoxz.oneboot.common.config.CommonProperties;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PsysConfig implements OnebootModuleConfig {
    @Resource
    private PsysProperties psysProperties;
    @Resource
    private CommonProperties commonProperties;

    @Override
    public String getModuleName() {
        return psysProperties.getModuleName();
    }

    @Override
    public String getPackage() {
        return commonProperties.getBasePackage() + "." + psysProperties.getModuleName();
    }
}
