package com.github.alphafoxz.oneboot.domain.preset_sys.access_control.command;

import com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo.AbacAttrVo;
import com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo.ResourceActionType;
import com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo.ResourceType;
import jakarta.annotation.Nonnull;

/**
 * 添加资源事件
 */
public record AccessControlAddResourceCommand(
        @Nonnull
        java.util.Set<ResourceActionType> actionTypeSet,
        @Nonnull
        java.util.Set<AbacAttrVo> attrSet, Long businessId, Boolean enabled,
        @Nonnull
        java.time.OffsetDateTime expireTime, Long ownerSubjectId,
        @Nonnull
        ResourceType resourceType, String schemaName, String tableName
) {
}