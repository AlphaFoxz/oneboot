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
 * @created 03-7月-2024 7:16:45
 */
@lombok.experimental.Accessors(chain = true)
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.Getter
public class RegisterCommand {

    private Email email;
    private Ip ip;
    private Password password;
    private Phone phone;
    private Username username;

}//end RegisterCommand