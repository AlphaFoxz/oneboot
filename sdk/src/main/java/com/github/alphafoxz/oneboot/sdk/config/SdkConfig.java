package com.github.alphafoxz.oneboot.sdk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SdkConfig {
    @Autowired
    public void init(ServerProperties serverProperties) {
        String info = """
                 .d88b.  d8b   db d88888b d8888b.  .d88b.   .d88b.  d888888b        .d8888. d8888b. db   dD\s
                .8P  Y8. 888o  88 88'     88  `8D .8P  Y8. .8P  Y8. `~~88~~'        88'  YP 88  `8D 88 ,8P'\s
                88    88 88V8o 88 88ooooo 88oooY' 88    88 88    88    88           `8bo.   88   88 88,8P  \s
                88    88 88 V8o88 88~~~~~ 88~~~b. 88    88 88    88    88    C8888D   `Y8b. 88   88 88`8b  \s
                `8b  d8' 88  V888 88.     88   8D `8b  d8' `8b  d8'    88           db   8D 88  .8D 88 `88.\s
                 `Y88P'  VP   V8P Y88888P Y8888P'  `Y88P'   `Y88P'     YP           `8888Y' Y8888D' YP   YD\s
                """;
        info += "\n页面导航请访问 http://127.0.0.1:" + serverProperties.getPort() + "/_sdk";
        System.err.println(info);
    }
}
