package com.github.alphafoxz.oneboot.core.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "oneboot.core")
public class CoreProperties {
    private String basePackage = "com.github.alphafoxz.oneboot";
    private SnowflakePropertiesBean snowflake;

    @Data
    public static class SnowflakePropertiesBean {
        private Long workerId;
        private Long datacenterId;
    }

}
