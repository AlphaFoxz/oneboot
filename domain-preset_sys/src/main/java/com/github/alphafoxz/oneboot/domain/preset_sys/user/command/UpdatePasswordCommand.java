package com.github.alphafoxz.oneboot.domain.preset_sys.user.command;

import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.Password;

/**
 * 更新密码命令
 * @author Wong
 * @version 1.0
 * @created 03-7月-2024 7:16:45
 */
@lombok.experimental.Accessors(chain = true)
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.Getter
public class UpdatePasswordCommand {

    private Password newPassword;
    private Password oldPassword;
    private Long userId;

}//end UpdatePasswordCommand