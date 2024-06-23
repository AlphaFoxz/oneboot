package com.github.alphafoxz.oneboot.domain.preset_sys.access_control.command;

import com.github.alphafoxz.oneboot.domain.preset_sys.access_control.vo.DacType;

/**
 * 删除动态访问控制命令
 * @author Wong
 * @version 1.0
 * @created 22-6月-2024 1:59:36
 */
@lombok.experimental.Accessors(chain = true)
@lombok.Setter(lombok.AccessLevel.PACKAGE)
@lombok.NoArgsConstructor(access = lombok.AccessLevel.PACKAGE)
@lombok.Getter(lombok.AccessLevel.PUBLIC)
public class DelDacCommand {

	private DacType dacType;
	private Long operatorSubjectId;
	private Long resourceId;
	private Long subjectId;
	private Long targetSubjectId;
	private java.time.OffsetDateTime time;

}//end DelDacCommand