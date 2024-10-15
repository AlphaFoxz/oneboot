package com.github.alphafoxz.oneboot.domain.preset_sys.user.command;

import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.IpVo;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.PasswordVo;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.UsernameVo;
import org.springframework.lang.NonNull;

/**
 * 登录命令
 */
public record UserLoginCommand(
        @NonNull
        UsernameVo username,
        @NonNull
        PasswordVo password,
        @NonNull
        IpVo ip
) {
}