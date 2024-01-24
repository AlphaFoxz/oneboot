package com.github.alphafoxz.oneboot.common.configuration;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfiguration {
    @Resource
    private CommonProperties commonProperties;
}
