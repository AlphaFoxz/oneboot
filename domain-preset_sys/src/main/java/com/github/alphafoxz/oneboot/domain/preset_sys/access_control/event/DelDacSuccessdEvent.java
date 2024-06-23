package com.github.alphafoxz.oneboot.domain.preset_sys.access_control.event;


/**
 * 删除动态访问控制成功事件
 * @author Wong
 * @version 1.0
 * @created 22-6月-2024 1:59:36
 */
@lombok.experimental.Accessors(chain = true)
@lombok.Setter(lombok.AccessLevel.PACKAGE)
@lombok.NoArgsConstructor(access = lombok.AccessLevel.PACKAGE)
@lombok.Getter(lombok.AccessLevel.PUBLIC)
public class DelDacSuccessdEvent {

	private Long operatorSubjectId;
	private Long resourceId;
	private java.time.OffsetDateTime time;

}//end DelDacSuccessdEvent