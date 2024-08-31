package com.github.alphafoxz.oneboot.core.configuration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Getter
public class CoreConfiguration {
    private final CoreProperties coreProperties;
}
