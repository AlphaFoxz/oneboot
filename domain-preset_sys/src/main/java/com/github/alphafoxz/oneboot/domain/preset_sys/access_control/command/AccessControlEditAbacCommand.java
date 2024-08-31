package com.github.alphafoxz.oneboot.domain.preset_sys.access_control.command;


import com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo.AbacAttrVo;

/**
 * 编辑属性访问控制命令
 */
public record AccessControlEditAbacCommand(
        AbacAttrVo abacAttr,
        Long operatorSubjectId,
        Long subjectId,
        java.time.OffsetDateTime time) {
}