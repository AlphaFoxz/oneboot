package com.github.alphafoxz.oneboot.domain.preset_sys.user;

import com.github.alphafoxz.oneboot.core.toolkit.coding.SpringUtil;

public interface PasswordEncoder {
    public static PasswordEncoder getInstance() {
        if (PasswordEncoder.Instance.VALUE == null) {
            PasswordEncoder.Instance.VALUE = SpringUtil.getBean(PasswordEncoder.class);
        }
        return PasswordEncoder.Instance.VALUE;
    }

    class Instance {
        private static PasswordEncoder VALUE = null;
    }

    public static void setInstance(PasswordEncoder instance) {
        if (PasswordEncoder.Instance.VALUE != null) {
            throw new RuntimeException("Instance already exists");
        }
        PasswordEncoder.Instance.VALUE = instance;
    }

    public String encode(CharSequence rawPassword);

    public Boolean matches(CharSequence rawPassword, String encodedPassword);

    public Boolean isValid(String encodedPassword);
}
