package com.github.alphafoxz.oneboot.app.configuration;

import cn.hutool.core.lang.Snowflake;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Bean
    public Snowflake snowflake() {
        return new Snowflake();
    }
}
