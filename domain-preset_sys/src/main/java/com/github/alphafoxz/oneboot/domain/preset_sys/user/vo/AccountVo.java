package com.github.alphafoxz.oneboot.domain.preset_sys.user.vo;

import lombok.Builder;

import java.time.OffsetDateTime;

/**
 * 账号
 */
@Builder(toBuilder = true)
public record AccountVo(
        Long id,
        PasswordVo password,
        EmailVo email,
        PhoneVo phone,
        OffsetDateTime createTime,
        AccountStatus status
) {
}