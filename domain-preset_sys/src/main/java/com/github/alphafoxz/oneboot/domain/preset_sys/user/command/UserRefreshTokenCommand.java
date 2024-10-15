package com.github.alphafoxz.oneboot.domain.preset_sys.user.command;

import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.TokenVo;
import org.springframework.lang.NonNull;

/**
 * 刷新令牌命令
 */
public record UserRefreshTokenCommand(
        @NonNull
        Long userId,
        TokenVo token
) {
}