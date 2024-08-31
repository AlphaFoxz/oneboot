package com.github.alphafoxz.oneboot.domain.preset_sys.access_control.event;

import com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo.AbacAttrVo;

/**
 * 编辑属性访问控制成功事件
 */
public record AccessControlEditAttrSucceededEvent(
        Long operatorSubjectId,
        Long subjectId,
        AbacAttrVo abacAttr,
        java.time.OffsetDateTime time
) {
}