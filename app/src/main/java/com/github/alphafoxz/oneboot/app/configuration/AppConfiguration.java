package com.github.alphafoxz.oneboot.app.configuration;

import com.github.alphafoxz.oneboot.core.configuration.CoreProperties;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Resource
    private AppProperties appProperties;
    @Resource
    private CoreProperties coreProperties;
}
