package com.github.alphafoxz.oneboot.domain.preset_sys.user.entity;

import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.NicknameVo;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.PhoneVo;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.UserStatus;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.UsernameVo;

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
    private Long id;
    private UsernameVo username;
    private NicknameVo nickname;
    private java.util.Map<String, String> abacAttrs;
    private PhoneVo phone;
    private UserStatus status;
    private java.time.OffsetDateTime createTime;
    private java.time.OffsetDateTime updateTime;
}