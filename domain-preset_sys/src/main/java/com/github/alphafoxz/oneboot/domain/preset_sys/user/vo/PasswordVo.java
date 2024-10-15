package com.github.alphafoxz.oneboot.domain.preset_sys.user.vo;

import com.github.alphafoxz.oneboot.core.domain.DomainArgCheckException;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * 密码
 */
public record PasswordVo(
        @NonNull
        String value,
        @Nullable
        Boolean _isEncrypt
) {
    public PasswordVo {
        if (_isEncrypt == null) {
            _isEncrypt = true;
        }
        if (!_isEncrypt && value.length() > 20) {
            throw new DomainArgCheckException("密码不能超过20个字符");
        } else if (_isEncrypt && value.length() != 60) {
            throw new DomainArgCheckException("密码格式错误");
        }
    }
}