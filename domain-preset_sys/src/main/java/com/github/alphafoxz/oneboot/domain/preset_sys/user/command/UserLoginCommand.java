package com.github.alphafoxz.oneboot.domain.preset_sys.user.command;

import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.IpVo;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.PasswordVo;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.UsernameVo;
import jakarta.annotation.Nonnull;

/**
 * 登录命令
 */
public record UserLoginCommand(
        @Nonnull
        UsernameVo username,
        @Nonnull
        PasswordVo password,
        @Nonnull
        IpVo ip
) {
}//end LoginCommand