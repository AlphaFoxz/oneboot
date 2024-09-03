module oneboot.core {
    requires static lombok;

    requires cn.hutool;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires io.swagger.v3.oas.models;
    requires jakarta.annotation;
    requires java.scripting;
    requires java.desktop;
    requires org.mapstruct;
    requires org.springdoc.openapi.common;
    requires org.slf4j;
    requires spring.boot;
    requires spring.context;
    requires spring.core;
    requires spring.web;
    requires spring.beans;

    exports com.github.alphafoxz.oneboot.core.toolkit.coding;
    exports com.github.alphafoxz.oneboot.core.toolkit.tuple;
    exports com.github.alphafoxz.oneboot.core;
    exports com.github.alphafoxz.oneboot.core.annotations.spring;
    opens com.github.alphafoxz.oneboot.core.configuration;
    exports com.github.alphafoxz.oneboot.core.exceptions;
    exports com.github.alphafoxz.oneboot.core.domain;
    exports com.github.alphafoxz.oneboot.core.standard.access_control;
    exports com.github.alphafoxz.oneboot.core.standard.persistence;
    exports com.github.alphafoxz.oneboot.core.standard.service;
    exports com.github.alphafoxz.oneboot.core.standard.restful;
    exports com.github.alphafoxz.oneboot.core.annotations;
//    exports com.github.alphafoxz.oneboot.core.annotations.lombok;
}