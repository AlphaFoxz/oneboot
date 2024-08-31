package com.github.alphafoxz.oneboot.domain.preset_sys.user.vo;


import com.github.alphafoxz.oneboot.domain.preset_sys.DomainVoException;
import jakarta.annotation.Nonnull;
import org.springframework.http.HttpStatus;

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
            throw new DomainVoException("密码不能超过20个字符", HttpStatus.BAD_REQUEST);
        } else if (isEncrypt && value.length() != 60) {
            throw new DomainVoException("密码格式错误", HttpStatus.BAD_REQUEST);
        }
    }
}