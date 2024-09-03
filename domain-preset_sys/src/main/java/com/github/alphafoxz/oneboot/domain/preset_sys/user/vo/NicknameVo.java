package com.github.alphafoxz.oneboot.domain.preset_sys.user.vo;


import com.github.alphafoxz.oneboot.core.domain.DomainArgCheckException;

/**
 * 昵称
 */
public record NicknameVo(String value) {
    public NicknameVo {
        if (value.length() > 20) {
            throw new DomainArgCheckException("昵称不能超过20个字符");
        }
    }
}//end Nickname