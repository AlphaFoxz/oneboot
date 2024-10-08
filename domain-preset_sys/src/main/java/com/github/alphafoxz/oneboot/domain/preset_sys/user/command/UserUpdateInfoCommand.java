package com.github.alphafoxz.oneboot.domain.preset_sys.user.command;

import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.EmailVo;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.NicknameVo;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.PhoneVo;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.UsernameVo;
import jakarta.annotation.Nonnull;

/**
 * 更新用户信息命令
 */
public record UserUpdateInfoCommand(
        @Nonnull
        Long userId,
        EmailVo email,
        NicknameVo nickname,
        PhoneVo phone,
        UsernameVo username) {
}