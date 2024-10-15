package com.github.alphafoxz.oneboot.domain.preset_sys.access_control.command;


import com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo.AbacAttrVo;
import org.springframework.lang.NonNull;

/**
 * 添加属性访问控制命令
 */
public record AccessControlAddAbacCommand(
        @NonNull
        Long subjectId,
        @NonNull
        java.util.Set<AbacAttrVo> abacAttr,
        @NonNull
        Long operatorSubjectId,
        java.time.OffsetDateTime time
) {
}