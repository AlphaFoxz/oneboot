package com.github.alphafoxz.oneboot.domain.preset_sys.user.entity;

import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.Nickname;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.Phone;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.UserStatus;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.Username;

/**
 * @author Wong
 * @version 1.0
 * @created 03-7月-2024 7:16:45
 */
@lombok.experimental.Accessors(chain = true)
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.Getter
public class User {

    private java.util.Map<String, String> abacAttrs;
    private java.time.OffsetDateTime createTime;
    private Long id;
    private Nickname nickname;
    private Phone phone;
    private UserStatus status;
    private java.time.OffsetDateTime updateTime;
    private Username username;

}//end User