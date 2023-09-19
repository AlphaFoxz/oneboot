package com.github.alphafoxz.oneboot.sdk.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "oneboot.sdk")
public class SdkProperties {
    private String moduleName = "sdk";

    private ThriftProperties thrift;

    @Data
    public static class ThriftProperties {
        private Integer port = 8081;
        private TServerEnum tServer = TServerEnum.T_THREADED_SELECTOR_SERVER;
    }

    public static enum TServerEnum {
        T_THREAD_POOL_SERVER,
        T_SIMPLE_SERVER,
        T_THREADED_SELECTOR_SERVER
    }
}
