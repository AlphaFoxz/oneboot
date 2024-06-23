package com.github.alphafoxz.oneboot.domain.preset_sys.user.event;

import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.Ip;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.Token;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.Username;

/**
 * 登录成功事件
 * @author Wong
 * @version 1.0
 * @created 22-6月-2024 1:59:37
 */
@lombok.experimental.Accessors(chain = true)
@lombok.Setter(lombok.AccessLevel.PACKAGE)
@lombok.NoArgsConstructor(access = lombok.AccessLevel.PACKAGE)
@lombok.Getter(lombok.AccessLevel.PUBLIC)
public class LoginSuccessdEvent {

	private Ip ip;
	private java.time.OffsetDateTime time;
	private Token token;
	private Long userId;
	private Username username;

}//end LoginSuccessdEvent