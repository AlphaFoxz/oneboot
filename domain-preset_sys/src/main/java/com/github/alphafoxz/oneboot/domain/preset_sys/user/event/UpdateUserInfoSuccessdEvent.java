package com.github.alphafoxz.oneboot.domain.preset_sys.user.event;

import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.Username;

/**
 * 更新用户信息成功事件
 * @author Wong
 * @version 1.0
 * @created 22-6月-2024 1:59:38
 */
@lombok.experimental.Accessors(chain = true)
@lombok.Setter(lombok.AccessLevel.PACKAGE)
@lombok.NoArgsConstructor(access = lombok.AccessLevel.PACKAGE)
@lombok.Getter(lombok.AccessLevel.PUBLIC)
public class UpdateUserInfoSuccessdEvent {

	private java.time.OffsetDateTime time;
	private Long userId;
	private Username username;

}//end UpdateUserInfoSuccessdEvent