package com.github.alphafoxz.oneboot.domain.preset_sys.user.vo;

import com.github.alphafoxz.oneboot.core.domain.DomainArgCheckException;
import com.github.alphafoxz.oneboot.core.toolkit.coding.ReUtil;
import com.github.alphafoxz.oneboot.core.toolkit.coding.StrUtil;
import org.springframework.lang.NonNull;

/**
 * 手机号
 */
public record PhoneVo(@NonNull String value) {
    public PhoneVo {
        if (StrUtil.isBlank(value)) {
            throw new DomainArgCheckException("手机号不能为空");
        } else if (!ReUtil.isMatch("1\\d{10}", value)) {
            throw new DomainArgCheckException("手机号格式错误");
        }
    }
}