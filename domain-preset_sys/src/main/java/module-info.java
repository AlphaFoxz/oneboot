module oneboot.domain.preset_sys {
    requires static lombok;

    requires spring.context;

    exports com.github.alphafoxz.oneboot.domain.preset_sys.user.event;
    exports com.github.alphafoxz.oneboot.domain.preset_sys.user.entity;
    exports com.github.alphafoxz.oneboot.domain.preset_sys.user.repo;
    exports com.github.alphafoxz.oneboot.domain.preset_sys.user;
}