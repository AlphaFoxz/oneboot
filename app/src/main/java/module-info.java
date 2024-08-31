module oneboot.app {
    requires static lombok;

    requires io.swagger.v3.oas.annotations;
    requires io.swagger.v3.oas.models;
    requires jakarta.annotation;
    requires oneboot.core;
    requires oneboot.preset_sys;
    requires org.springdoc.openapi.common;
    requires spring.beans;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.web;


    opens com.github.alphafoxz.oneboot.app;
    opens com.github.alphafoxz.oneboot.app.configuration;
}