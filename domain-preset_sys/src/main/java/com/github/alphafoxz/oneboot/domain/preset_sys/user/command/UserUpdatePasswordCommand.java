package com.github.alphafoxz.oneboot.domain.preset_sys.user.command;

import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.PasswordVo;
import org.springframework.lang.NonNull;

/**
 * 更新密码命令
 */
public record UserUpdatePasswordCommand(
        @NonNull
        Long userId,
        @NonNull
        PasswordVo newPassword,
        @NonNull
        PasswordVo oldPassword
) {
}