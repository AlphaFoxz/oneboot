package com.github.alphafoxz.oneboot.domain.preset_sys.user.vo;

import com.github.alphafoxz.oneboot.core.domain.DomainEnum;

/**
 * 用户状态
 */
public enum UserStatus implements DomainEnum<UserStatus> {
    /**
     * 启用
     */
    ENABLED,
    /**
     * 禁用
     */
    DISABLED;

}