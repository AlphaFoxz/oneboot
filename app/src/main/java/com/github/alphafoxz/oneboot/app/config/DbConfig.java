package com.github.alphafoxz.oneboot.app.config;

import com.zaxxer.hikari.HikariDataSource;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbConfig {
    @Bean
    public DSLContext dslContext(HikariDataSource hikariDataSource) {
        return DSL.using(hikariDataSource, SQLDialect.POSTGRES);
    }
}
