package com.github.alphafoxz.oneboot.domain.preset_sys.access_control.event;


import java.time.OffsetDateTime;

/**
 * 添加资源成功事件
 */
public record AccessControlAddResourceSucceededEvent(
        Long resourceId,
        Long subjectId,
        OffsetDateTime time
) {
}