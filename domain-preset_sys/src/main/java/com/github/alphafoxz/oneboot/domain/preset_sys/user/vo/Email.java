package com.github.alphafoxz.oneboot.domain.preset_sys.user.vo;


import com.github.alphafoxz.oneboot.core.exceptions.OnebootBadRequestException;
import com.github.alphafoxz.oneboot.core.toolkit.coding.ReUtil;
import org.springframework.http.HttpStatus;

/**
 * 电子邮箱
 *
 * @author Wong
 * @version 1.0
 * @created 03-7月-2024 7:16:44
 */
@lombok.experimental.Accessors(chain = true)
@lombok.Setter
@lombok.Getter
public class Email {

    private String value;

    public Email(String value) {
        if (!ReUtil.isMatch("\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}", value)) {
            throw new OnebootBadRequestException("电子邮箱格式不正确", HttpStatus.BAD_REQUEST);
        }
        this.value = value;
    }
}//end Email