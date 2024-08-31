package com.github.alphafoxz.oneboot.domain.preset_sys.user.vo;


import com.github.alphafoxz.oneboot.domain.preset_sys.DomainVoException;
import org.springframework.http.HttpStatus;

/**
 * 昵称
 */
public record NicknameVo(String value) {
    public NicknameVo {
        if (value.length() > 20) {
            throw new DomainVoException("昵称不能超过20个字符", HttpStatus.BAD_REQUEST);
        }
    }
}//end Nickname