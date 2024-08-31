package com.github.alphafoxz.oneboot.domain.preset_sys.user.vo;


import com.github.alphafoxz.oneboot.core.toolkit.coding.ReUtil;
import com.github.alphafoxz.oneboot.domain.preset_sys.DomainVoException;
import org.springframework.http.HttpStatus;

/**
 * 电子邮箱
 */
public record EmailVo(String value) {
    public EmailVo {
        if (!ReUtil.isMatch("\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}", value)) {
            throw new DomainVoException("电子邮箱格式不正确", HttpStatus.BAD_REQUEST);
        }
    }
}//end Email