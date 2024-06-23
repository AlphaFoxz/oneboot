package com.github.alphafoxz.oneboot.domain.preset_sys.access_control.command;


/**
 * 编辑属性访问控制命令
 * @author Wong
 * @version 1.0
 * @created 22-6月-2024 1:59:36
 */
@lombok.experimental.Accessors(chain = true)
@lombok.Setter(lombok.AccessLevel.PACKAGE)
@lombok.NoArgsConstructor(access = lombok.AccessLevel.PACKAGE)
@lombok.Getter(lombok.AccessLevel.PUBLIC)
public class EditAbacCommand {

	private int abacAttr;
	private Long operatorSubjectId;
	private Long subjectId;
	private java.time.OffsetDateTime time;

}//end EditAbacCommand