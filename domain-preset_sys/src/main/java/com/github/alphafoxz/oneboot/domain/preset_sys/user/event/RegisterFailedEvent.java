package com.github.alphafoxz.oneboot.domain.preset_sys.user.event;

import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.Ip;

/**
 * 注册失败事件
 * @author Wong
 * @version 1.0
 * @created 22-6月-2024 1:59:37
 */
@lombok.experimental.Accessors(chain = true)
@lombok.Setter(lombok.AccessLevel.PACKAGE)
@lombok.NoArgsConstructor(access = lombok.AccessLevel.PACKAGE)
@lombok.Getter(lombok.AccessLevel.PUBLIC)
public class RegisterFailedEvent {

	private Ip ip;
	private java.time.OffsetDateTime time;

}//end RegisterFailedEvent