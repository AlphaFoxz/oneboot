package com.github.alphafoxz.oneboot.domain.preset_sys.user;

public interface PasswordEncoder {
    public String encode(CharSequence rawPassword);

    public Boolean matches(CharSequence rawPassword, String encodedPassword);

    public Boolean isValid(String encodedPassword);
}
