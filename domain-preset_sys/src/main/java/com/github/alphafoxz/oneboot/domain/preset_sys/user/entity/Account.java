package com.github.alphafoxz.oneboot.domain.preset_sys.user.entity;


import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.Email;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.Password;

/**
 * 账号
 * @author Wong
 * @version 1.0
 * @created 03-7月-2024 7:16:43
 */
@lombok.experimental.Accessors(chain = true)
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.Getter
public class Account {

    private java.time.OffsetDateTime createTime;
    private Email email;
    private String id;
    private Password password;
    private String phone;
    private java.time.OffsetDateTime updateTime;

}//end Account