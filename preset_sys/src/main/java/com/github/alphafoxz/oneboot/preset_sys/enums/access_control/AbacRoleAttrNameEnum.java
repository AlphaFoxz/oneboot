package com.github.alphafoxz.oneboot.preset_sys.enums.access_control;

import com.github.alphafoxz.oneboot.core.standard.access_control.AbacAttrName;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
public enum AbacRoleAttrNameEnum implements AbacAttrName, GrantedAuthority {
    SECURITY_ADMIN("SECURITY_ADMIN");

    private final String name;

    AbacRoleAttrNameEnum(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
