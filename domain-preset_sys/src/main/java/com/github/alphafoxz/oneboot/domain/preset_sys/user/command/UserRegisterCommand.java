package com.github.alphafoxz.oneboot.domain.preset_sys.user.command;

import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.*;

/**
 * 注册命令
 */
public record UserRegisterCommand(
        UsernameVo username,
        PasswordVo password,
        EmailVo email,
        PhoneVo phone,
        IpVo ip
) {
}