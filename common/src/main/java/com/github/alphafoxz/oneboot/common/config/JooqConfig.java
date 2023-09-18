package com.github.alphafoxz.oneboot.common.config;

import com.zaxxer.hikari.HikariDataSource;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JooqConfig {
    @Bean
    public DSLContext dslContext(HikariDataSource hikariDataSource) {
        return DSL.using(hikariDataSource, SQLDialect.POSTGRES);
    }

    @Bean
    public DataSourceConnectionProvider dataSourceConnectionProvider(HikariDataSource hikariDataSource) {
        return new DataSourceConnectionProvider(hikariDataSource);
    }

    @Bean
    public org.jooq.Configuration jooqConfiguration(HikariDataSource hikariDataSource) {
        org.jooq.Configuration configuration = new DefaultConfiguration();
        configuration.set(hikariDataSource);
        return configuration;
    }
}
