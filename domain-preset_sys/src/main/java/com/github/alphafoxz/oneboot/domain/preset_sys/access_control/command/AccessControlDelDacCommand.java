package com.github.alphafoxz.oneboot.domain.preset_sys.access_control.command;

import com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo.DacType;

/**
 * 删除动态访问控制命令
 */
public record AccessControlDelDacCommand(
        DacType dacType,
        Long operatorSubjectId,
        Long resourceId,
        Long subjectId,
        Long targetSubjectId, java.time.OffsetDateTime time) {
}