package com.github.alphafoxz.oneboot.core.configuration;

import cn.hutool.core.lang.Snowflake;
import com.github.alphafoxz.oneboot.core.toolkit.coding.IdUtil;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdConfiguration {
    @Resource
    private CoreProperties coreProperties;

    @Bean
    public Snowflake snowflake() {
        return IdUtil.getSnowflake(coreProperties.getSnowflake().getWorkerId(), coreProperties.getSnowflake().getDatacenterId());
    }
}
