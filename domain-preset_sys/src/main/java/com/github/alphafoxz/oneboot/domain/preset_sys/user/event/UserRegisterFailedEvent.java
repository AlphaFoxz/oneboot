package com.github.alphafoxz.oneboot.domain.preset_sys.user.event;

import com.github.alphafoxz.oneboot.domain.preset_sys.DomainEvent;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.IpVo;
import jakarta.annotation.Nonnull;

/**
 * 注册失败事件
 */
public record UserRegisterFailedEvent(
        @Nonnull
        IpVo ip,
        @Nonnull
        String reason,
        @Nonnull
        java.time.OffsetDateTime time
) implements DomainEvent {
}