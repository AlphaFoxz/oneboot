package com.github.alphafoxz.oneboot.domain.preset_sys.user.command;

import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.TokenVo;
import jakarta.annotation.Nonnull;

/**
 * 登出命令
 */
public record UserLogoutCommand(
        @Nonnull
        Long userId,
        TokenVo token
) {
}