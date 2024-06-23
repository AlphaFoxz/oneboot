package com.github.alphafoxz.oneboot.domain.preset_sys.access_control.command;

import com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo.AbacAttr;
import com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo.ResourceActionType;
import com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo.ResourceType;

/**
 * 添加资源事件
 * @author Wong
 * @version 1.0
 * @created 22-6月-2024 1:59:36
 */
@lombok.experimental.Accessors(chain = true)
@lombok.Setter(lombok.AccessLevel.PACKAGE)
@lombok.NoArgsConstructor(access = lombok.AccessLevel.PACKAGE)
@lombok.Getter(lombok.AccessLevel.PUBLIC)
public class AddResourceCommand {

	private java.util.Set<ResourceActionType> actionTypeSet;
	private java.util.Set<AbacAttr> attrSet;
	private Long businessId;
	private Boolean enabled;
	private java.time.OffsetDateTime expireTime;
	private Long ownerSubjectId;
	private ResourceType resourceType;
	private String schemaName;
	private String tableName;

}//end AddResourceCommand