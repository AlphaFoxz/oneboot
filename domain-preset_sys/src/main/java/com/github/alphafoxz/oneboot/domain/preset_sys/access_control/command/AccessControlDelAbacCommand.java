package com.github.alphafoxz.oneboot.domain.preset_sys.access_control.command;

import com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo.AbacAttrVo;

/**
 * 删除属性访问控制命令
 */
public record AccessControlDelAbacCommand(
        Long subjectId,
        Long operatorSubjectId,
        AbacAttrVo abacAttr,
        java.time.OffsetDateTime time
) {
}