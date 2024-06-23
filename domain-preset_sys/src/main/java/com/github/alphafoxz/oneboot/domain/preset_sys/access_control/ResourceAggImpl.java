package com.github.alphafoxz.oneboot.domain.preset_sys.access_control;

import com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo.AbacAttr;
import com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo.ResourceActionType;
import com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo.ResourceType;

/**
 * 资源聚合
 * @author Wong
 * @version 1.0
 * @created 22-6月-2024 1:59:37
 */
@lombok.experimental.Accessors(chain = true)
@lombok.Setter(lombok.AccessLevel.PACKAGE)
@lombok.NoArgsConstructor(access = lombok.AccessLevel.PACKAGE)
@lombok.Getter(lombok.AccessLevel.PUBLIC)
public class ResourceAggImpl implements ResourceAgg {

	private java.util.Set<ResourceActionType> actionTypeSet;
	private java.util.Set<AbacAttr> attrSet;
	private Long businessId;
	private java.time.OffsetDateTime createTime;
	private Boolean enabled;
	private java.time.OffsetDateTime expireTime;
	private ResourceType resourceType;
	private String schemaName;
	private Long subjectId;
	private String tableName;
	private java.time.OffsetDateTime updateTime;

}//end ResourceAggImpl