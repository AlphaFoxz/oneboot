package com.github.alphafoxz.oneboot.domain.preset_sys.user.event;

import com.github.alphafoxz.oneboot.core.domain.DomainEvent;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.UsernameVo;
import org.springframework.lang.NonNull;

/**
 * 更新用户信息成功事件
 */
public record UserUpdateInfoSucceededEvent(
        @NonNull
        Long userId,
        @NonNull
        UsernameVo username,
        @NonNull
        java.time.OffsetDateTime time
) implements DomainEvent {
}