package com.github.alphafoxz.oneboot.domain.preset_sys.user.command;

import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.Email;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.Ip;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.Password;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.Phone;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.Username;

/**
 * 注册命令
 * @author Wong
 * @version 1.0
 * @created 22-6月-2024 1:59:37
 */
@lombok.experimental.Accessors(chain = true)
@lombok.Setter(lombok.AccessLevel.PACKAGE)
@lombok.NoArgsConstructor(access = lombok.AccessLevel.PACKAGE)
@lombok.Getter(lombok.AccessLevel.PUBLIC)
public class RegisterCommand {

	private Email email;
	private Ip ip;
	private Password password;
	private Phone phone;
	private Username username;

}//end RegisterCommand