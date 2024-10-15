package com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo;

import lombok.Builder;

/**
 * 动态访问控制
 */
@Builder(toBuilder = true)
public record DacRecordVo(
        DacType dacType,
        Long resourceId
) {
}