package com.github.alphafoxz.oneboot.domain.preset_sys.user.vo;

import com.github.alphafoxz.oneboot.core.domain.DomainArgCheckException;
import com.github.alphafoxz.oneboot.core.toolkit.coding.StrUtil;
import lombok.Builder;
import org.springframework.lang.NonNull;

import java.time.OffsetDateTime;

/**
 * 令牌
 */
@Builder(toBuilder = true)
public record TokenVo(
        Long id,
        @NonNull
        String accessToken,
        @NonNull
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
}
