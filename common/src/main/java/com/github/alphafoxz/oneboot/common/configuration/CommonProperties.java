package com.github.alphafoxz.oneboot.common.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "oneboot.common")
public class CommonProperties {
    private CacheStrategyEnum cacheStrategy = CacheStrategyEnum.CAFFEINE;
    private String basePackage = "com.github.alphafoxz.oneboot";
    private String moduleName = "common";
    private SnowflakePropertiesBean snowflake;

    @Data
    public static class SnowflakePropertiesBean {
        private Long workerId;
        private Long datacenterId;
    }

    public static enum CacheStrategyEnum {
        CAFFEINE,
        REDIS;
    }
}
