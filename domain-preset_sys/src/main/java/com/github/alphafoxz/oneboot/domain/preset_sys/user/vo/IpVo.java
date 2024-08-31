package com.github.alphafoxz.oneboot.domain.preset_sys.user.vo;


import com.github.alphafoxz.oneboot.core.toolkit.coding.ReUtil;
import com.github.alphafoxz.oneboot.domain.preset_sys.DomainVoException;
import org.springframework.http.HttpStatus;

/**
 * IP地址
 */
public record IpVo(String value) {
    public IpVo {
        if (!ReUtil.isMatch("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}", value)) {
            throw new DomainVoException("IP地址格式不正确", HttpStatus.BAD_REQUEST);
        }
    }
}//end Ip