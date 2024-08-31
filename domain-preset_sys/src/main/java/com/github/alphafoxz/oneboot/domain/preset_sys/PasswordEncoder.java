package com.github.alphafoxz.oneboot.domain.preset_sys;

import com.github.alphafoxz.oneboot.core.toolkit.coding.SpringUtil;

public interface PasswordEncoder {
    public static PasswordEncoder getInstance() {
        return SpringUtil.getBean(PasswordEncoder.class);
    }

    public String encode(CharSequence rawPassword);

    public Boolean matches(CharSequence rawPassword, String encodedPassword);
}
