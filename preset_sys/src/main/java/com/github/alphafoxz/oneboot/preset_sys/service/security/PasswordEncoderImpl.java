package com.github.alphafoxz.oneboot.preset_sys.service.security;

import com.github.alphafoxz.oneboot.common.toolkit.coding.StrUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncoderImpl implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return StrUtil.equals(rawPassword, encodedPassword);
    }
}
