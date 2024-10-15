package com.github.alphafoxz.oneboot.domain.preset_sys.user.event;

import com.github.alphafoxz.oneboot.core.domain.DomainEvent;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.IpVo;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.UsernameVo;
import org.springframework.lang.NonNull;

/**
 * 登录失败事件
 */
public record UserLoginFailedEvent(
        Long userId,
        @NonNull
        UsernameVo username,
        @NonNull
        String reason,
        @NonNull
        java.time.OffsetDateTime time,
        @NonNull
        IpVo ip
) implements DomainEvent {
}