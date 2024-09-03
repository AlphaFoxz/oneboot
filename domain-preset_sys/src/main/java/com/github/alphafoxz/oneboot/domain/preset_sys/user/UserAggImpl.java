package com.github.alphafoxz.oneboot.domain.preset_sys.user;

import com.github.alphafoxz.oneboot.core.annotations.Aggregate;
import com.github.alphafoxz.oneboot.core.domain.DomainBusinessException;
import com.github.alphafoxz.oneboot.core.domain.DomainEventPublisher;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.command.*;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.event.*;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.Account;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.PasswordVo;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.TokenVo;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.User;
import lombok.AllArgsConstructor;

import java.time.OffsetDateTime;

/**
 * 用户聚合
 */
@Aggregate
@AllArgsConstructor
public class UserAggImpl implements UserAgg {
    private Account account;
    private TokenVo token;
    private User user;

    @Override
    public void handleRegister(UserRegisterCommand command) {
        var now = OffsetDateTime.now();
        if (this.user != null) {
            var reason = "该用户名已存在";
            DomainEventPublisher.getInstance().publishEvent(new UserRegisterFailedEvent(command.ip(), reason, now));
            throw new DomainBusinessException(reason);
        } else if (account != null) {
            var reason = "该账号已存在（目前不支持一个账号，多个用户）";
            DomainEventPublisher.getInstance().publishEvent(new UserRegisterFailedEvent(command.ip(), reason, now));
            throw new DomainBusinessException(reason);
        }
        var encoder = PasswordEncoder.getInstance();
        var userId = UserRepo.getInstance().nextUserId();
        var accountId = UserRepo.getInstance().nextAccountId();
        var encryptedPassword = new PasswordVo(encoder.encode(command.password().value()), true);
        this.account = Account.builder()
                .id(accountId)
                .password(encryptedPassword)
                .email(command.email())
                .phone(command.phone())
                .createTime(now)
                .updateTime(now)
                .build();
        this.user = User.builder()
                .id(userId)
                .username(command.username())
                .createTime(now)
                .updateTime(now)
                .build();
        DomainEventPublisher.getInstance()
                .publishEvent(new UserRegisterSucceededEvent(userId, command.username(), now, command.ip()));
    }

    @Override
    public void handleLogin(UserLoginCommand command) {
        var now = OffsetDateTime.now();
        if (this.user == null) {
            var reason = "用户不存在";
            DomainEventPublisher.getInstance().publishEvent(new UserLoginFailedEvent(null, command.username(), reason, now, command.ip()));
            throw new DomainBusinessException(reason);
        }
        var encoder = PasswordEncoder.getInstance();
        if (!encoder.matches(command.password().value(), this.account.password().value())) {
            var reason = "密码不正确";
            DomainEventPublisher.getInstance().publishEvent(new UserLoginFailedEvent(this.user.id(), this.user.username(), reason, now, command.ip()));
            throw new DomainBusinessException(reason);
        }
        this.token = UserRepo.getInstance().createToken(this.user.id());
        DomainEventPublisher.getInstance().publishEvent(new UserLoginSucceededEvent(this.user.id(), this.user.username(), now, command.ip()));
    }

    @Override
    public void handleUpdateInfo(UserUpdateInfoCommand command) {
        if (this.user == null) {
            throw new DomainBusinessException("用户不存在");
        }
        var now = OffsetDateTime.now();
        this.account = this.account.toBuilder()
                .email(command.email())
                .phone(command.phone())
                .updateTime(now)
                .build();
        this.user = this.user.toBuilder()
                .nickname(command.nickname())
                .phone(command.phone())
                .updateTime(now)
                .build();
        DomainEventPublisher.getInstance().publishEvent(new UserUpdateInfoSucceededEvent(this.user.id(), command.username(), now));
    }

    @Override
    public void handleLogout(UserLogoutCommand command) {
        this.token = null;
    }

    @Override
    public TokenVo handleRefreshToken(UserRefreshTokenCommand command) {
        if (user == null) {
            throw new DomainBusinessException("用户不存在");
        } else if (this.token == null) {
            throw new DomainBusinessException("用户未登录");
        }
        this.token = UserRepo.getInstance().refreshToken(command.userId(), token);
        return this.token;
    }

    @Override
    public void handleUpdatePassword(UserUpdatePasswordCommand command) {
        var encoder = PasswordEncoder.getInstance();
        if (!encoder.matches(command.oldPassword().value(), this.account.password().value())) {
            throw new DomainBusinessException("旧密码不正确");
        }
        this.account = this.account.toBuilder()
                .password(new PasswordVo(encoder.encode(command.newPassword().value()), true))
                .updateTime(OffsetDateTime.now())
                .build();
    }

    public boolean hasLogin() {
        return token != null && PasswordEncoder.getInstance().isValid(account.password().value());
    }
}
