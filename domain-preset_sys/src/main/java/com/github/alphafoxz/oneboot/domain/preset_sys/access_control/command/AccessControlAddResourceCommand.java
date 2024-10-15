package com.github.alphafoxz.oneboot.domain.preset_sys.access_control.command;

import com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo.AbacAttrVo;
import com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo.ResourceActionType;
import com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo.ResourceType;
import org.springframework.lang.NonNull;

/**
 * 添加资源事件
 */
public record AccessControlAddResourceCommand(
        @NonNull
        java.util.Set<ResourceActionType> actionTypeSet,
        @NonNull
        java.util.Set<AbacAttrVo> attrSet, Long businessId, Boolean enabled,
        @NonNull
        java.time.OffsetDateTime expireTime, Long ownerSubjectId,
        @NonNull
        ResourceType resourceType, String schemaName, String tableName
) {
}