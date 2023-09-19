package com.github.alphafoxz.oneboot.preset_sys.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "oneboot.preset-sys")
public class PsysProperties {
    private String moduleName = "preset_sys";
}
