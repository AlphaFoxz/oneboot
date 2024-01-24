package com.github.alphafoxz.oneboot.app.configuration;

import com.github.alphafoxz.oneboot.common.configuration.CommonProperties;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Resource
    private AppProperties appProperties;
    @Resource
    private CommonProperties commonProperties;
}
