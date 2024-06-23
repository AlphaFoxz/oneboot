package com.github.alphafoxz.oneboot.domain.preset_sys.access_control.command;

import com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo.ResourceType;

/**
 * 删除资源事件
 * @author Wong
 * @version 1.0
 * @created 22-6月-2024 1:59:36
 */
@lombok.experimental.Accessors(chain = true)
@lombok.Setter(lombok.AccessLevel.PACKAGE)
@lombok.NoArgsConstructor(access = lombok.AccessLevel.PACKAGE)
@lombok.Getter(lombok.AccessLevel.PUBLIC)
public class DelResourceCommand {

	private Long businessId;
	private Long ownerSubjectId;
	private ResourceType resourceType;

}//end DelResourceCommand