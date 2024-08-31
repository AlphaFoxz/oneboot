module oneboot.preset_sys {
    requires static lombok;

    requires cn.hutool;
    requires com.nimbusds.jose.jwt;
    requires io.swagger.v3.oas.annotations;
    requires jakarta.validation;
    requires oneboot.core;
    requires oneboot.domain.preset_sys;
    requires org.apache.tomcat.embed.core;
    requires org.aspectj.weaver;
    requires org.mapstruct;
    requires org.slf4j;
    requires spring.context;
    requires spring.core;
    requires spring.data.commons;
    requires spring.security.config;
    requires spring.security.core;
    requires spring.security.crypto;
    requires spring.security.web;
    requires spring.web;
    requires spring.webmvc;

    opens com.github.alphafoxz.oneboot.preset_sys.configuration;
//    opens com.github.alphafoxz.oneboot.preset_sys.gen.db.entity;

    exports com.github.alphafoxz.oneboot.preset_sys.service.abac.policy;
    exports com.github.alphafoxz.oneboot.preset_sys.service.framework;
    exports com.github.alphafoxz.oneboot.preset_sys.service.security;
}