package com.github.alphafoxz.oneboot.domain.preset_sys.access_control.event;


import java.time.OffsetDateTime;

/**
 * 删除动态访问控制成功事件
 */
public record AccessControlDelDacSucceededEvent(
        Long operatorSubjectId,
        Long resourceId,
        OffsetDateTime time
) {
}