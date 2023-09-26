package com.github.alphafoxz.oneboot.preset_sys.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Data
@Component
@ConfigurationProperties(prefix = "oneboot.preset-sys")
public class PsysProperties {
    private String moduleName = "preset_sys";
    private JwtProperties jwt;

    /**
     * jwt配置
     */
    @Data
    public static class JwtProperties {
        private Long accessTokenExpireTime = 30L;
        private TimeUnit accessTokenExpireTimeUnit = TimeUnit.MINUTES;
        private Long refreshTokenExpireTime = 8L;
        private TimeUnit refreshTokenExpireTimeUnit = TimeUnit.HOURS;
    }
}
