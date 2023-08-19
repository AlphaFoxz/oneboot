package com.github.alphafoxz.oneboot.common.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI restfulOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Oneboot API文档")
                        .description("基于SpringDoc生成的API文档")
                        .version("0.1.0-alpha.0")
                        .license(new License().name("Apache 2.0").url("https://github.com/AlphaFoxz/oneboot/blob/main/LICENSE")))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringDoc Documentation")
                        .url("https://springdoc.org/"));
    }
}
