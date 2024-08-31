package com.github.alphafoxz.oneboot.domain.preset_sys.access_control.command;

import com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo.ResourceType;

/**
 * 删除资源事件
 */
public record AccessControlDelResourceCommand(
        Long businessId,
        Long ownerSubjectId,
        ResourceType resourceType
) {
}