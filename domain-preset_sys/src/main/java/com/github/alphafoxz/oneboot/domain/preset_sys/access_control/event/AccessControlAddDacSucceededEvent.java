package com.github.alphafoxz.oneboot.domain.preset_sys.access_control.event;

import java.time.OffsetDateTime;

/**
 * 添加动态访问控制成功事件
 */
public record AccessControlAddDacSucceededEvent(
        Long operatorSubjectId,
        Long resourceId,
        OffsetDateTime time
) {
}