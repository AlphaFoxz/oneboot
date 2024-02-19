package com.github.alphafoxz.oneboot.preset_sys.gen.restl.dtos._compile_only;

/**
 * 用户令牌结果
 */
public interface PsysAuthTokenInfoDtoFields {
    /**
     * 用户名
     */
    public static final String USERNAME = "username";

    /**
     * 当前登录用户的角色
     */
    public static final String ROLES = "roles";

    /**
     * 访问令牌
     */
    public static final String ACCESS_TOKEN = "accessToken";

    /**
     * 刷新令牌
     */
    public static final String REFRESH_TOKEN = "refreshToken";

    /**
     * accessToken的过期时间（格式'yyyy/MM/dd HH:mm:ss'）
     */
    public static final String EXPIRES = "expires";

}