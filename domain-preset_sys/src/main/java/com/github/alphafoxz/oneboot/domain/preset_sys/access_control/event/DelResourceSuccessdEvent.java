package com.github.alphafoxz.oneboot.domain.preset_sys.access_control.event;


/**
 * 删除资源成功事件
 * @author Wong
 * @version 1.0
 * @created 22-6月-2024 1:59:36
 */
@lombok.experimental.Accessors(chain = true)
@lombok.Setter(lombok.AccessLevel.PACKAGE)
@lombok.NoArgsConstructor(access = lombok.AccessLevel.PACKAGE)
@lombok.Getter(lombok.AccessLevel.PUBLIC)
public class DelResourceSuccessdEvent {

	private Long resourceId;
	private Long subjectId;
	private java.time.OffsetDateTime time;

}//end DelResourceSuccessdEvent