package com.github.alphafoxz.oneboot.domain.preset_sys.access_control.command;

import com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo.DacType;

/**
 * 编辑动态访问控制命令
 */
public record AccessControlEditDacCommand(
        Long id,
        DacType dacType,
        java.time.OffsetDateTime expireTime,
        Long operatorSubjectId,
        Long ownerSubjectId,
        Long resourceId,
        Long targetSubjectId,
        java.time.OffsetDateTime time
) {
}