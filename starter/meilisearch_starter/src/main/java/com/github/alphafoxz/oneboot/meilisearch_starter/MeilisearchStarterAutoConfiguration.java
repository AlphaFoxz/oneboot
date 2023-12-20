package com.github.alphafoxz.oneboot.meilisearch_starter;

import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Config;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@Slf4j
@AutoConfiguration
@EnableConfigurationProperties(MeilisearchStarterProperties.class)
public class MeilisearchStarterAutoConfiguration {
    @Resource
    private MeilisearchStarterProperties meilisearchStarterProperties;

    @Bean
    public Client client() {
        return new Client(new Config(meilisearchStarterProperties.getUrl(), meilisearchStarterProperties.getMasterKey()));
    }
}
