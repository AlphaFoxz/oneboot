package com.github.alphafoxz.oneboot.domain.preset_sys.user.vo;


import com.github.alphafoxz.oneboot.core.toolkit.coding.StrUtil;
import com.github.alphafoxz.oneboot.domain.preset_sys.DomainVoException;
import jakarta.annotation.Nonnull;
import org.springframework.http.HttpStatus;

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
            throw new DomainVoException("访问令牌不能为空", HttpStatus.BAD_REQUEST);
        }
        if (StrUtil.isBlank(refreshToken)) {
            throw new DomainVoException("刷新令牌不能为空", HttpStatus.BAD_REQUEST);
        }
    }
}//end Token