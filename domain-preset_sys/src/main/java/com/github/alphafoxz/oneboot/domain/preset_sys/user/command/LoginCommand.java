package com.github.alphafoxz.oneboot.domain.preset_sys.user.command;

import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.Ip;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.Password;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.Username;

/**
 * 登录命令
 * @author Wong
 * @version 1.0
 * @created 03-7月-2024 7:16:44
 */
@lombok.experimental.Accessors(chain = true)
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.Getter
public class LoginCommand {

    private Ip ip;
    /**
     * 登录指令
     */
    private Password password;
    private Username username;

}//end LoginCommand