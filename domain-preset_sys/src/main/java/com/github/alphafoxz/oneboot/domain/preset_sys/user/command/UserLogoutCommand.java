package com.github.alphafoxz.oneboot.domain.preset_sys.user.command;

import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.TokenVo;
import org.springframework.lang.NonNull;

/**
 * 登出命令
 */
public record UserLogoutCommand(
        @NonNull
        Long userId,
        TokenVo token
) {
}