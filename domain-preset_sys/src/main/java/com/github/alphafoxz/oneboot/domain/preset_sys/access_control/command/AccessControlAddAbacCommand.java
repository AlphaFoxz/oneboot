package com.github.alphafoxz.oneboot.domain.preset_sys.access_control.command;


import com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo.AbacAttrVo;
import jakarta.annotation.Nonnull;

/**
 * 添加属性访问控制命令
 */
public record AccessControlAddAbacCommand(
        @Nonnull
        Long subjectId,
        @Nonnull
        java.util.Set<AbacAttrVo> abacAttr,
        @Nonnull
        Long operatorSubjectId,
        java.time.OffsetDateTime time
) {
}