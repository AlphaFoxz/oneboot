package com.github.alphafoxz.oneboot.domain.preset_sys.user;


import com.github.alphafoxz.oneboot.domain.preset_sys.user.command.*;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.TokenVo;

/**
 * 用户聚合
 */
public interface UserAgg {
    public void handleRegister(UserRegisterCommand command);

    public void handleLogin(UserLoginCommand command);

    public void handleUpdateInfo(UserUpdateInfoCommand command);

    public void handleLogout(UserLogoutCommand command);

    public TokenVo handleRefreshToken(UserRefreshTokenCommand command);

    public void handleUpdatePassword(UserUpdatePasswordCommand command);
}