package com.github.alphafoxz.oneboot.core.configuration;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreConfiguration {
    @Resource
    private CoreProperties coreProperties;
}
