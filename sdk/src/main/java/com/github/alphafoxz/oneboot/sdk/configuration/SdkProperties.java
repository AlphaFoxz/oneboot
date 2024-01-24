package com.github.alphafoxz.oneboot.sdk.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "oneboot.sdk")
public class SdkProperties {
}
