package com.github.alphafoxz.oneboot.domain.preset_sys.user.event;

import com.github.alphafoxz.oneboot.core.domain.DomainEvent;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.IpVo;
import org.springframework.lang.NonNull;

/**
 * 注册失败事件
 */
public record UserRegisterFailedEvent(
        @NonNull
        IpVo ip,
        @NonNull
        String reason,
        @NonNull
        java.time.OffsetDateTime time
) implements DomainEvent {
}