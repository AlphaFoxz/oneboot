package com.github.alphafoxz.oneboot.domain.preset_sys.user;

import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.*;

import java.time.OffsetDateTime;

class Const {
    public static final UsernameVo adminUsername = new UsernameVo("admin");
    public static final TokenVo token = new TokenVo(1L, "accessToken", "refreshToken", OffsetDateTime.now());
    public static final PasswordVo rawPassword = new PasswordVo("123456", false);
    public static final PasswordVo encryptedPassword = new PasswordVo("$2a$10$kdPO80r0qE.qqbh4tLxx0uSOKwGDqYa6pN/HhaC87ywZYbmzPUPZ6", true);
    public static final OffsetDateTime now = OffsetDateTime.now();
    public static final AccountVo adminAccount = AccountVo.builder()
            .id(1L)
            .phone(new PhoneVo("12345678901"))
            .email(new EmailVo("admin@163.com"))
            .password(encryptedPassword)
            .createTime(now)
            .build();
    public static final UserVo adminUser = UserVo.builder()
            .id(1L)
            .username(adminUsername)
            .nickname(new NicknameVo("管理员"))
            .abacAttrs(new java.util.HashMap<>())
            .phone(new PhoneVo("12345678901"))
            .status(UserStatus.ENABLED)
            .createTime(now)
            .build();
    public static final UserAgg adminUserAgg = new UserAggImpl(adminAccount, token, adminUser);
}