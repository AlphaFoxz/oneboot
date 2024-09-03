package com.github.alphafoxz.oneboot.domain.preset_sys.user.vo;


import com.github.alphafoxz.oneboot.core.domain.DomainArgCheckException;
import com.github.alphafoxz.oneboot.core.toolkit.coding.StrUtil;
import jakarta.annotation.Nonnull;

/**
 * 用户名
 */
public record UsernameVo(@Nonnull String value) {
    public UsernameVo {
        if (StrUtil.isBlank(value)) {
            throw new DomainArgCheckException("用户名不能为空");
        }
    }
}//end Username