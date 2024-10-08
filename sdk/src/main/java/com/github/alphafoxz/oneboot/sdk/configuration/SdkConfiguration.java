package com.github.alphafoxz.oneboot.sdk.configuration;

import cn.hutool.core.lang.Snowflake;
import com.github.alphafoxz.oneboot.core.configuration.CoreProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SdkConfiguration {
    private final SdkProperties sdkProperties;
    private final CoreProperties coreProperties;

    @Autowired
    public void init(ServerProperties serverProperties) {
        String text = """
                
                
                 .d88b.  d8b   db d88888b d8888b.  .d88b.   .d88b.  d888888b        .d8888. d8888b. db   dD\s
                .8P  Y8. 888o  88 88'     88  `8D .8P  Y8. .8P  Y8. `~~88~~'        88'  YP 88  `8D 88 ,8P'\s
                88    88 88V8o 88 88ooooo 88oooY' 88    88 88    88    88           `8bo.   88   88 88,8P  \s
                88    88 88 V8o88 88~~~~~ 88~~~b. 88    88 88    88    88    C8888D   `Y8b. 88   88 88`8b  \s
                `8b  d8' 88  V888 88.     88   8D `8b  d8' `8b  d8'    88           db   8D 88  .8D 88 `88.\s
                 `Y88P'  VP   V8P Y88888P Y8888P'  `Y88P'   `Y88P'     YP           `8888Y' Y8888D' YP   YD\s
                
                """;
        text += "\n页面导航请访问 http://127.0.0.1:" + serverProperties.getPort() + "/_sdk";
        text += "\nAPI页面导航请访问 http://127.0.0.1:" + serverProperties.getPort() + "/swagger-ui.html";
        log.info(text);
    }

    @Bean
    @ConditionalOnMissingBean(Snowflake.class)
    public Snowflake snowflake() {
        return new Snowflake(1L, 1L);
    }
}
