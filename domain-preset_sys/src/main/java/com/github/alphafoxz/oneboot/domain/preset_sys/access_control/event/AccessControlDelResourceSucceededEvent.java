package com.github.alphafoxz.oneboot.domain.preset_sys.access_control.event;


import java.time.OffsetDateTime;

/**
 * 删除资源成功事件
 */
public record AccessControlDelResourceSucceededEvent(
        Long resourceId,
        Long subjectId,
        OffsetDateTime time
) {
}