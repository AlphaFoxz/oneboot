package com.github.alphafoxz.oneboot.domain.preset_sys.user.command;

import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.Ip;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.Password;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.Username;

/**
 * 登录命令
 * @author Wong
 * @version 1.0
 * @created 22-6月-2024 1:59:37
 */
@lombok.experimental.Accessors(chain = true)
@lombok.Setter(lombok.AccessLevel.PACKAGE)
@lombok.NoArgsConstructor(access = lombok.AccessLevel.PACKAGE)
@lombok.Getter(lombok.AccessLevel.PUBLIC)
public class LoginCommand {

	private Ip ip;
	/**
	 * 登录指令
	 */
	private Password password;
	private Username username;

}//end LoginCommand