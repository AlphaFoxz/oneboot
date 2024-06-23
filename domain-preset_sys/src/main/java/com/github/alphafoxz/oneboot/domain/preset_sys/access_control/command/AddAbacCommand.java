package com.github.alphafoxz.oneboot.domain.preset_sys.access_control.command;


import com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo.AbacAttr;

/**
 * 添加属性访问控制命令
 * @author Wong
 * @version 1.0
 * @created 22-6月-2024 1:59:36
 */
@lombok.experimental.Accessors(chain = true)
@lombok.Setter(lombok.AccessLevel.PACKAGE)
@lombok.NoArgsConstructor(access = lombok.AccessLevel.PACKAGE)
@lombok.Getter(lombok.AccessLevel.PUBLIC)
public class AddAbacCommand {

	private java.util.Set<AbacAttr> abacAttr;
	private Long operatorSubjectId;
	private Long subjectId;
	private java.time.OffsetDateTime time;

}//end AddAbacCommand