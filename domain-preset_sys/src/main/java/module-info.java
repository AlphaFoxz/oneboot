module oneboot.domain.preset_sys {
    requires static lombok;

    requires cn.hutool;
    requires jakarta.annotation;
    requires oneboot.core;
    requires spring.context;
    requires spring.web;

    exports com.github.alphafoxz.oneboot.domain.preset_sys.access_control.command;
    exports com.github.alphafoxz.oneboot.domain.preset_sys.access_control.event;
    exports com.github.alphafoxz.oneboot.domain.preset_sys.access_control.entity;
    exports com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo;
    exports com.github.alphafoxz.oneboot.domain.preset_sys.access_control;

    exports com.github.alphafoxz.oneboot.domain.preset_sys.user.command;
    exports com.github.alphafoxz.oneboot.domain.preset_sys.user.event;
    exports com.github.alphafoxz.oneboot.domain.preset_sys.user.entity;
    exports com.github.alphafoxz.oneboot.domain.preset_sys.user.vo;
    exports com.github.alphafoxz.oneboot.domain.preset_sys.user;
}