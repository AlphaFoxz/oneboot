package com.github.alphafoxz.oneboot.preset_sys.domain.user;

import com.github.alphafoxz.oneboot.domain.preset_sys.user.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderImpl implements PasswordEncoder {
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public String encode(CharSequence rawPassword) {
        return encoder.encode(rawPassword);
    }

    @Override
    public Boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }

    @Override
    public Boolean isValid(String encodedPassword) {
        return encoder.upgradeEncoding(encodedPassword);
    }
}
