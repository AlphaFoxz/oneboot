package com.github.alphafoxz.oneboot.domain.preset_sys.user.event;

import com.github.alphafoxz.oneboot.core.domain.DomainEvent;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.IpVo;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.UsernameVo;
import org.springframework.lang.NonNull;

/**
 * 注册成功事件
 */
public record UserRegisterSucceededEvent(
        @NonNull
        Long userId,
        @NonNull
        UsernameVo username,
        @NonNull
        java.time.OffsetDateTime time,
        @NonNull
        IpVo ip
) implements DomainEvent {
}