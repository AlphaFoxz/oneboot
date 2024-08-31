package com.github.alphafoxz.oneboot.domain.preset_sys.user.command;

import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.TokenVo;
import jakarta.annotation.Nonnull;

/**
 * 刷新令牌命令
 */
public record UserRefreshTokenCommand(
        @Nonnull
        Long userId,
        TokenVo token
) {
}