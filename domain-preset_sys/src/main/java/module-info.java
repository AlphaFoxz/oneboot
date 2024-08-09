module oneboot.domain.preset_sys {
    requires static lombok;

    requires cn.hutool;
    requires oneboot.core;
    requires spring.context;
    requires spring.web;

    exports com.github.alphafoxz.oneboot.domain.preset_sys.user.event;
    exports com.github.alphafoxz.oneboot.domain.preset_sys.user.entity;
    exports com.github.alphafoxz.oneboot.domain.preset_sys.user.repo;
    exports com.github.alphafoxz.oneboot.domain.preset_sys.user;
}