package com.github.alphafoxz.oneboot.domain.preset_sys.user.vo;


import com.github.alphafoxz.oneboot.core.exceptions.OnebootBadRequestException;
import com.github.alphafoxz.oneboot.core.toolkit.coding.ReUtil;
import org.springframework.http.HttpStatus;

/**
 * IP地址
 *
 * @author Wong
 * @version 1.0
 * @created 03-7月-2024 7:16:44
 */
@lombok.experimental.Accessors(chain = true)
@lombok.Setter
@lombok.Getter
public class Ip {

    private String value;

    public Ip(String value) {
        if (!ReUtil.isMatch("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}", value)) {
            throw new OnebootBadRequestException("IP地址格式不正确", HttpStatus.BAD_REQUEST);
        }
        this.value = value;
    }
}//end Ip