package com.github.alphafoxz.oneboot.domain.preset_sys.user.event;

import com.github.alphafoxz.oneboot.domain.preset_sys.DomainEvent;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.IpVo;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.UsernameVo;
import jakarta.annotation.Nonnull;

/**
 * 登录失败事件
 */
public record UserLoginFailedEvent(
        Long userId,
        @Nonnull
        UsernameVo username,
        @Nonnull
        String reason,
        @Nonnull
        java.time.OffsetDateTime time,
        @Nonnull
        IpVo ip
) implements DomainEvent {
}