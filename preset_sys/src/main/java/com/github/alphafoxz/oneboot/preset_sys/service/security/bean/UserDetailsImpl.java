package com.github.alphafoxz.oneboot.preset_sys.service.security.bean;

import com.github.alphafoxz.oneboot.common.toolkit.coding.CollUtil;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.text.ParseException;
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

    @SuppressWarnings("unchecked")
    public static <T extends UserDetails> UserDetailsImpl from(JWTClaimsSet jwtClaimsSet) throws ParseException {
        UserDetailsImpl userDetailsImpl = new UserDetailsImpl();
        userDetailsImpl.setSubjectId(Long.parseLong(jwtClaimsSet.getSubject()));
        userDetailsImpl.setUsername(jwtClaimsSet.getStringClaim("username"));
        userDetailsImpl.setPassword(jwtClaimsSet.getStringClaim("password"));
        userDetailsImpl.setAuthorities((Collection<? extends GrantedAuthority>) jwtClaimsSet.getClaim("authorities"));
        userDetailsImpl.setEnabled(jwtClaimsSet.getBooleanClaim("enabled"));
        userDetailsImpl.setAccountNonExpired(jwtClaimsSet.getBooleanClaim("accountNonExpired"));
        userDetailsImpl.setAccountNonLocked(jwtClaimsSet.getBooleanClaim("accountNonLocked"));
        userDetailsImpl.setCredentialsNonExpired(jwtClaimsSet.getBooleanClaim("credentialsNonExpired"));
        return userDetailsImpl;
    }

    public static <T extends UserDetails> UserDetailsImpl from(T userDetails) {
        UserDetailsImpl userDetailsImpl = new UserDetailsImpl();
        userDetailsImpl.setUsername(userDetails.getUsername());
        userDetailsImpl.setPassword(userDetails.getPassword());
        userDetailsImpl.setAuthorities(userDetails.getAuthorities());
        userDetailsImpl.setEnabled(userDetails.isEnabled());
        userDetailsImpl.setAccountNonExpired(userDetails.isAccountNonExpired());
        userDetailsImpl.setAccountNonLocked(userDetails.isAccountNonLocked());
        userDetailsImpl.setCredentialsNonExpired(userDetails.isCredentialsNonExpired());
        userDetailsImpl.setSubjectId(userDetailsImpl.getSubjectId());
        return userDetailsImpl;
    }
}
