package com.github.alphafoxz.oneboot.preset_sys.pojo.security;

import com.github.alphafoxz.oneboot.common.toolkit.coding.CollUtil;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 用户信息（用于spring security相关功能）
 */
@Data
public class UserDetailsImpl implements UserDetails {
    /**
     * 主体id
     */
    private Long subjectId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 账户未过期
     */
    private boolean accountNonExpired = true;
    /**
     * 账户未锁定
     */
    private boolean accountNonLocked = true;
    /**
     * 是否可用
     */
    private boolean enabled = true;
    /**
     * 密码未失效
     */
    private boolean credentialsNonExpired = true;
    /**
     * 授予权限
     */
    private Collection<? extends GrantedAuthority> authorities = CollUtil.newArrayList();
}
