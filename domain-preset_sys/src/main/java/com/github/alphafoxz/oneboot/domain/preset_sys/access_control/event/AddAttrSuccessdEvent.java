package com.github.alphafoxz.oneboot.domain.preset_sys.access_control.event;

import com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo.AbacAttr;

/**
 * 添加属性成功事件
 * @author Wong
 * @version 1.0
 * @created 22-6月-2024 1:59:36
 */
@lombok.experimental.Accessors(chain = true)
@lombok.Setter(lombok.AccessLevel.PACKAGE)
@lombok.NoArgsConstructor(access = lombok.AccessLevel.PACKAGE)
@lombok.Getter(lombok.AccessLevel.PUBLIC)
public class AddAttrSuccessdEvent {

	private AbacAttr abacAttr;
	private Long operatorSubjectId;
	private Long subjectId;
	private java.time.OffsetDateTime time;

}//end AddAttrSuccessdEvent