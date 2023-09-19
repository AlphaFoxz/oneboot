package com.github.alphafoxz.oneboot.preset_sys.configuration;

import com.github.alphafoxz.oneboot.common.interfaces.OnebootModuleConfig;
import com.github.alphafoxz.oneboot.common.configuration.CommonProperties;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PsysConfiguration implements OnebootModuleConfig {
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
