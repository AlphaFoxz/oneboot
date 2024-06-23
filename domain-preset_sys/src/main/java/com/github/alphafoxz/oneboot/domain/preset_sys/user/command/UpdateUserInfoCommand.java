package com.github.alphafoxz.oneboot.domain.preset_sys.user.command;

import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.Email;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.Nickname;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.Phone;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.Username;

/**
 * 更新用户信息命令
 * @author Wong
 * @version 1.0
 * @created 22-6月-2024 1:59:38
 */
@lombok.experimental.Accessors(chain = true)
@lombok.Setter(lombok.AccessLevel.PACKAGE)
@lombok.NoArgsConstructor(access = lombok.AccessLevel.PACKAGE)
@lombok.Getter(lombok.AccessLevel.PUBLIC)
public class UpdateUserInfoCommand {

	private Email email;
	private Nickname nickname;
	private Phone phone;
	private Long userId;
	private Username username;

}//end UpdateUserInfoCommand