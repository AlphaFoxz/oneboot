package com.github.alphafoxz.oneboot.domain.preset_sys.user.event;

import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.UsernameVo;
import org.springframework.lang.NonNull;

/**
 * 更新密码成功事件
 */
public record UserUpdatePasswordSucceededEvent(
        @NonNull
        Long userId,
        @NonNull
        UsernameVo username,
        @NonNull
        java.time.OffsetDateTime time
) {
}