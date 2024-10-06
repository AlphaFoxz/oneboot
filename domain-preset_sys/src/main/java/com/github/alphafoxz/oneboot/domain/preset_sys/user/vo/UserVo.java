package com.github.alphafoxz.oneboot.domain.preset_sys.user.vo;

import lombok.Builder;

import java.time.OffsetDateTime;

/**
 * 用户
 */
@Builder(toBuilder = true)
public record UserVo(
        Long id,
        UsernameVo username,
        NicknameVo nickname,
        java.util.Map<String, String> abacAttrs,
        PhoneVo phone,
        UserStatus status,
        OffsetDateTime createTime
) {
}