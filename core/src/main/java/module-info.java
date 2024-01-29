module oneboot.core {
    requires cn.hutool;
    requires org.springdoc.openapi.common;
    requires io.swagger.v3.oas.models;
    requires spring.context;
    requires org.jooq;
    requires static lombok;
    requires spring.core;
    requires spring.web;
    requires com.github.benmanes.caffeine;
    requires spring.context.support;
    requires spring.data.redis;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires com.fasterxml.jackson.databind;
    requires jakarta.annotation;
    requires spring.boot;
    requires spring.data.commons;
    requires org.slf4j;
    requires com.zaxxer.hikari;
    requires java.scripting;
    requires java.desktop;

    exports com.github.alphafoxz.oneboot.core.standard.starter.meilisearch;
    exports com.github.alphafoxz.oneboot.core.standard.starter.flowable;
    exports com.github.alphafoxz.oneboot.core.toolkit.coding;
}