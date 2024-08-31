package com.github.alphafoxz.oneboot.domain.preset_sys.user.command;

import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.PasswordVo;
import jakarta.annotation.Nonnull;

/**
 * 更新密码命令
 */
public record UserUpdatePasswordCommand(
        @Nonnull
        Long userId,
        @Nonnull
        PasswordVo newPassword,
        @Nonnull
        PasswordVo oldPassword
) {
}