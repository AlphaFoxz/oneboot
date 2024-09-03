package com.github.alphafoxz.oneboot.domain.preset_sys.user.vo;


import com.github.alphafoxz.oneboot.core.domain.DomainArgCheckException;
import jakarta.annotation.Nonnull;

/**
 * 密码
 */
public record PasswordVo(
        @Nonnull
        String value,
        @Nonnull
        Boolean isEncrypt
) {
    public PasswordVo {
        if (!isEncrypt && value.length() > 20) {
            throw new DomainArgCheckException("密码不能超过20个字符");
        } else if (isEncrypt && value.length() != 60) {
            throw new DomainArgCheckException("密码格式错误");
        }
    }
}