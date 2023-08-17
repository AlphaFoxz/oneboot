package com.github.alphafoxz.oneboot.sdk.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "oneboot.sdk")
public class SdkProperties {
    private ThriftProperties thrift;

    @Data
    public static class ThriftProperties {
        private Integer port;
        private TServerEnum tServer;
    }

    public static enum TServerEnum {
        T_THREAD_POOL_SERVER,
        T_SIMPLE_SERVER,
        T_THREADED_SELECTOR_SERVER
    }
}
