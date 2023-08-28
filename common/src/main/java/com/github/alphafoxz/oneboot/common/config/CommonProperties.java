package com.github.alphafoxz.oneboot.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "oneboot.common")
public class CommonProperties {
    private String basePackage = "com.github.alphafoxz.oneboot";
    private String moduleName = "common";
    private SnowflakePropertiesBean snowflake;

    @Data
    public static class SnowflakePropertiesBean {
        private Long workerId;
        private Long datacenterId;
    }
}
