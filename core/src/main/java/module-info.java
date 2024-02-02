module com.github.alphafoxz.oneboot.core {
    requires cn.hutool;
    requires org.springdoc.openapi.common;
    requires io.swagger.v3.oas.models;
    requires spring.context;
    requires static lombok;
    requires spring.core;
    requires spring.web;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires com.fasterxml.jackson.databind;
    requires jakarta.annotation;
    requires spring.boot;
    requires spring.data.commons;
    requires org.slf4j;
    requires java.scripting;
    requires java.desktop;

    exports com.github.alphafoxz.oneboot.core.toolkit.coding;
    exports com.github.alphafoxz.oneboot.core;
    exports com.github.alphafoxz.oneboot.core.annotations.spring;
    exports com.github.alphafoxz.oneboot.core.configuration;
}