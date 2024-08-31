package com.github.alphafoxz.oneboot.domain.preset_sys.user.vo;


import com.github.alphafoxz.oneboot.core.toolkit.coding.StrUtil;
import com.github.alphafoxz.oneboot.domain.preset_sys.DomainVoException;
import jakarta.annotation.Nonnull;
import org.springframework.http.HttpStatus;

/**
 * 用户名
 */
public record UsernameVo(@Nonnull String value) {
    public UsernameVo {
        if (StrUtil.isBlank(value)) {
            throw new DomainVoException("用户名不能为空", HttpStatus.BAD_REQUEST);
        }
    }
}//end Username