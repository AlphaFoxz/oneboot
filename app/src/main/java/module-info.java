module oneboot.app {
    requires static lombok;

    requires io.swagger.v3.oas.annotations;
    requires jakarta.annotation;
    requires oneboot.core;
    requires oneboot.preset_sys;
    requires spring.beans;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.data.jpa;
    requires spring.web;


    opens com.github.alphafoxz.oneboot.app;
    opens com.github.alphafoxz.oneboot.app.configuration;
}