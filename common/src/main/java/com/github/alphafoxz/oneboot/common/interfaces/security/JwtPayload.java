package com.github.alphafoxz.oneboot.common.interfaces.security;


import com.github.alphafoxz.oneboot.common.interfaces.access_control.AbacAttrName;

import java.util.Collection;

public interface JwtPayload {
    Long getSubjectId();

    Collection<? extends AbacAttrName> getAuthorities();

    String getPassword();

    String getUsername();

    boolean isAccountNonExpired();

    boolean isAccountNonLocked();

    boolean isCredentialsNonExpired();

    boolean isEnabled();
}
