package com.github.alphafoxz.oneboot.domain.preset_sys.access_control.event;

import com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo.AbacAttrVo;

import java.time.OffsetDateTime;

/**
 * 添加属性成功事件
 */
public record AccessControlAddAttrSucceededEvent(
        AbacAttrVo abacAttr,
        Long operatorSubjectId,
        Long subjectId,
        OffsetDateTime time
) {
}