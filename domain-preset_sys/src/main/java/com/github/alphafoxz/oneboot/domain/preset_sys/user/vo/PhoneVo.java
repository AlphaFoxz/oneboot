package com.github.alphafoxz.oneboot.domain.preset_sys.user.vo;

import com.github.alphafoxz.oneboot.core.toolkit.coding.ReUtil;
import com.github.alphafoxz.oneboot.core.toolkit.coding.StrUtil;
import com.github.alphafoxz.oneboot.domain.preset_sys.DomainVoException;
import jakarta.annotation.Nonnull;
import org.springframework.http.HttpStatus;

/**
 * 手机号
 */
public record PhoneVo(@Nonnull String value) {
    public PhoneVo {
        if (StrUtil.isBlank(value)) {
            throw new DomainVoException("手机号不能为空", HttpStatus.BAD_REQUEST);
        } else if (!ReUtil.isMatch("1\\d{10}", value)) {
            throw new DomainVoException("手机号格式错误", HttpStatus.BAD_REQUEST);
        }
    }
}//end Phone