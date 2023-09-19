package com.github.alphafoxz.oneboot.common.interfaces.security;

import org.springframework.lang.NonNull;

import java.io.Serializable;

public interface JwtService<USER extends Serializable> {
    public String token(@NonNull USER payload);

    public USER verify(@NonNull String token);

    public String refresh(@NonNull String token);
}
