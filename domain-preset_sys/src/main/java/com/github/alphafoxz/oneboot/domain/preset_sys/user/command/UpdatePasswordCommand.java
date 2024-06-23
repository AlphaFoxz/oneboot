package com.github.alphafoxz.oneboot.domain.preset_sys.user.command;

import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.Password;

/**
 * 更新密码命令
 * @author Wong
 * @version 1.0
 * @created 22-6月-2024 1:59:37
 */
@lombok.experimental.Accessors(chain = true)
@lombok.Setter(lombok.AccessLevel.PACKAGE)
@lombok.NoArgsConstructor(access = lombok.AccessLevel.PACKAGE)
@lombok.Getter(lombok.AccessLevel.PUBLIC)
public class UpdatePasswordCommand {

	private Password newPassword;
	private Password oldPassword;
	private Long userId;

}//end UpdatePasswordCommand