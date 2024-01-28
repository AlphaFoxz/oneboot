package com.github.alphafoxz.oneboot.core.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {
    @Bean
    public GroupedOpenApi appApi() {
        return GroupedOpenApi.builder()
                .group("oneboot-app")
                .pathsToMatch("/app/**")
                .build();
    }

    @Bean
    public GroupedOpenApi sdkApi() {
        return GroupedOpenApi.builder()
                .group("oneboot-sdk")
                .pathsToMatch("/_sdk/**")
                .build();
    }

    @Bean
    public GroupedOpenApi presetSysApi() {
        return GroupedOpenApi.builder()
                .group("oneboot-preset_sys")
                .pathsToMatch("/preset_sys/**")
//                .addOpenApiMethodFilter(method -> method.isAnnotationPresent(Admin.class))
                .build();
    }

    @Bean
    public OpenAPI indexApi() {
        return new OpenAPI()
                .info(getInfo())
                .externalDocs(new ExternalDocumentation()
                        .description("SpringDoc Documentation")
                        .url("https://springdoc.org/"));
    }

    private Info getInfo() {
        Info info = new Info().title("Oneboot API文档")
                .description("基于SpringDoc生成的API文档")
                .version("0.1.0-alpha.0")
                .license(new License().name("Apache 2.0").url("https://github.com/AlphaFoxz/oneboot/blob/main/LICENSE"));
        info.addExtension("sdk项目地址（java）", "https://github.com/AlphaFoxz/oneboot");
        info.addExtension("sdk项目地址（vue.js）", "待定");
        info.addExtension("sdk项目地址（rust）", "https://github.com/AlphaFoxz/oneboot_rust");
        Contact contact = new Contact();
        contact.setName("AlphaFoxz");
        contact.setEmail("841958335@qq.com");
        contact.setUrl("https://github.com/AlphaFoxz");
        info.contact(contact);
        return info;
    }
}
