package com.github.alphafoxz.oneboot.domain.preset_sys.access_control.event;

import com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo.AbacAttrVo;

import java.time.OffsetDateTime;

/**
 * 删除属性成功事件
 */
public record AccessControlDelAttrSucceededEvent(
        Long operatorSubjectId,
        Long subjectId,
        AbacAttrVo abacAttr,
        OffsetDateTime time
) {
}