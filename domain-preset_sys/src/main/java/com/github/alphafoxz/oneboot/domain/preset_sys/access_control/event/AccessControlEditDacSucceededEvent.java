package com.github.alphafoxz.oneboot.domain.preset_sys.access_control.event;


import java.time.OffsetDateTime;

/**
 * 编辑动态访问控制成功事件
 */
public record AccessControlEditDacSucceededEvent(
        Long operatorSubjectId,
        Long resourceId,
        OffsetDateTime time
) {
}