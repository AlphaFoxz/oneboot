package com.github.alphafoxz.oneboot.core.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DatasourceConfiguration {
    @Bean
    public DSLContext dslContext(HikariDataSource hikariDataSource) {
        DSLContext dslContext = DSL.using(hikariDataSource, SQLDialect.POSTGRES);
        dslContext.execute("select 1");
        return dslContext;
    }

    @Bean
    public DataSourceConnectionProvider dataSourceConnectionProvider(HikariDataSource hikariDataSource) {
        return new DataSourceConnectionProvider(hikariDataSource);
    }

    @Bean
    @Primary
    public org.jooq.Configuration jooqConfiguration(HikariDataSource hikariDataSource) {
        org.jooq.Configuration configuration = new DefaultConfiguration();
        configuration.set(hikariDataSource);
        return configuration;
    }
}
