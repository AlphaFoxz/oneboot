package com.github.alphafoxz.oneboot.domain.preset_sys.user.vo;

import cn.hutool.core.util.EnumUtil;
import com.github.alphafoxz.oneboot.core.domain.DomainEnum;
import org.springframework.lang.Nullable;

import java.io.Serializable;

/**
 * 账户状态
 */
public enum AccountStatus implements DomainEnum<AccountStatus> {
    /**
     * 启用
     */
    ENABLED,
    /**
     * 禁用
     */
    DISABLED;

    @Nullable
    public static AccountStatus fromDbVal(Serializable dbVal) {
        return EnumUtil.fromString(AccountStatus.class, dbVal.toString());
    }
}