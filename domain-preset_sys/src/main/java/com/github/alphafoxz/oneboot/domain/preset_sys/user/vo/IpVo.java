package com.github.alphafoxz.oneboot.domain.preset_sys.user.vo;


import com.github.alphafoxz.oneboot.core.domain.DomainArgCheckException;
import com.github.alphafoxz.oneboot.core.toolkit.coding.ReUtil;

/**
 * IP地址
 */
public record IpVo(String value) {
    public IpVo {
        if (!ReUtil.isMatch("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}", value)) {
            throw new DomainArgCheckException("IP地址格式不正确");
        }
    }
}//end Ip