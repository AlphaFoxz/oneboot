package com.github.alphafoxz.oneboot.domain.preset_sys.access_control;

import com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo.AbacAttrVo;
import com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo.ResourceActionType;
import com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo.ResourceType;
import lombok.AllArgsConstructor;

/**
 * 资源聚合
 */
@AllArgsConstructor
public class ResourceAggImpl implements ResourceAgg {

    private java.util.Set<ResourceActionType> actionTypeSet;
    private java.util.Set<AbacAttrVo> attrSet;
    private Long businessId;
    private java.time.OffsetDateTime createTime;
    private Boolean enabled;
    private java.time.OffsetDateTime expireTime;
    private ResourceType resourceType;
    private String schemaName;
    private Long subjectId;
    private String tableName;
    private java.time.OffsetDateTime updateTime;

}