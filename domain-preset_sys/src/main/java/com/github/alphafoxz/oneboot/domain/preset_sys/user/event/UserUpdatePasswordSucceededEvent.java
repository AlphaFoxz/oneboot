package com.github.alphafoxz.oneboot.domain.preset_sys.user.event;

import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.UsernameVo;
import jakarta.annotation.Nonnull;

/**
 * 更新密码成功事件
 */
public record UserUpdatePasswordSucceededEvent(
        @Nonnull
        Long userId,
        @Nonnull
        UsernameVo username,
        @Nonnull
        java.time.OffsetDateTime time
) {
}