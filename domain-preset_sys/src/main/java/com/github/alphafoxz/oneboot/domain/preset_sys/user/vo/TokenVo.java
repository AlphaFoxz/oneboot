package com.github.alphafoxz.oneboot.domain.preset_sys.user.vo;


import com.github.alphafoxz.oneboot.core.domain.DomainArgCheckException;
import com.github.alphafoxz.oneboot.core.toolkit.coding.StrUtil;
import jakarta.annotation.Nonnull;

import java.time.OffsetDateTime;

/**
 * 令牌
 */
public record TokenVo(
        @Nonnull
        String accessToken,
        @Nonnull
        String refreshToken,
        OffsetDateTime expireTime
) {
    public TokenVo {
        if (StrUtil.isBlank(accessToken)) {
            throw new DomainArgCheckException("访问令牌不能为空");
        }
        if (StrUtil.isBlank(refreshToken)) {
            throw new DomainArgCheckException("刷新令牌不能为空");
        }
    }
}//end Token