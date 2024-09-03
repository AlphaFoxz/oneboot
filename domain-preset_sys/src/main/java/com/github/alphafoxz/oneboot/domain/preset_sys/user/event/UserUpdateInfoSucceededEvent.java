package com.github.alphafoxz.oneboot.domain.preset_sys.user.event;

import com.github.alphafoxz.oneboot.core.domain.DomainEvent;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.UsernameVo;
import jakarta.annotation.Nonnull;

/**
 * 更新用户信息成功事件
 */
public record UserUpdateInfoSucceededEvent(
        @Nonnull
        Long userId,
        @Nonnull
        UsernameVo username,
        @Nonnull
        java.time.OffsetDateTime time
) implements DomainEvent {
}