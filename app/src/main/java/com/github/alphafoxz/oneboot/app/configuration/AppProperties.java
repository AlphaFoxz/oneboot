package com.github.alphafoxz.oneboot.app.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "oneboot.app")
public class AppProperties {
    private String moduleName = "app";
    private ThriftProperties thrift;

    @Data
    public static class ThriftProperties {
        private Boolean enabled = false;
        private Integer port = 8081;
        private TServerEnum tServer = TServerEnum.T_THREADED_SELECTOR_SERVER;
    }

    public static enum TServerEnum {
        T_THREAD_POOL_SERVER,
        T_SIMPLE_SERVER,
        T_THREADED_SELECTOR_SERVER
    }
}
