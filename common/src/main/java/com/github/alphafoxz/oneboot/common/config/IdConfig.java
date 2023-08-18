package com.github.alphafoxz.oneboot.common.config;

import cn.hutool.core.lang.Snowflake;
import com.github.alphafoxz.oneboot.common.toolkit.coding.IdUtil;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdConfig {
    @Resource
    private CommonProperties commonProperties;

    @Bean
    public Snowflake snowflake() {
        return IdUtil.getSnowflake(commonProperties.getSnowflake().getWorkerId(), commonProperties.getSnowflake().getDatacenterId());
    }
}
