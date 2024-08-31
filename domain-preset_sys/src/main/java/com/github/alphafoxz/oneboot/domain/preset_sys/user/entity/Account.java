package com.github.alphafoxz.oneboot.domain.preset_sys.user.entity;


import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.EmailVo;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.PasswordVo;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.PhoneVo;

/**
 * 账号
 */
@lombok.experimental.Accessors(chain = true)
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.Getter
public class Account {

    private Long id;
    private PasswordVo password;
    private EmailVo email;
    private PhoneVo phone;
    private java.time.OffsetDateTime createTime;
    private java.time.OffsetDateTime updateTime;

}//end Account