package com.github.alphafoxz.oneboot.domain.preset_sys.user.vo;


import cn.hutool.core.util.EnumUtil;
import com.github.alphafoxz.oneboot.core.domain.DomainEnum;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * 用户状态
 */
@lombok.Getter
@RequiredArgsConstructor
public enum UserStatus implements DomainEnum<UserStatus> {
    /**
     * 启用
     */
    ENABLED,
    /**
     * 禁用
     */
    DISABLED;

    @Nullable
    public static UserStatus fromDbVal(Serializable dbVal) {
        return EnumUtil.fromString(UserStatus.class, dbVal.toString());
    }
}