package com.github.alphafoxz.oneboot.domain.preset_sys.user;

import com.github.alphafoxz.oneboot.core.configuration.BeanHolder;
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
            BeanHolder.get(DomainEventPublisher.class).publishEvent(new UserRegisterFailedEvent(command.ip(), reason, now));
            throw new DomainBusinessException(reason);
        } else if (account != null) {
            var reason = "该账号已存在（目前不支持一个账号，多个用户）";
            BeanHolder.get(DomainEventPublisher.class).publishEvent(new UserRegisterFailedEvent(command.ip(), reason, now));
            throw new DomainBusinessException(reason);
        }
        var encoder = BeanHolder.get(PasswordEncoder.class);
        var userId = BeanHolder.get(UserRepo.class).nextUserId();
        var accountId = BeanHolder.get(UserRepo.class).nextAccountId();
        var encryptedPassword = new PasswordVo(encoder.encode(command.password().value()), true);
        this.account = Account.builder()
                .id(accountId)
                .password(encryptedPassword)
                .email(command.email())
                .phone(command.phone())
                .createTime(now)
                .build();
        this.user = User.builder()
                .id(userId)
                .username(command.username())
                .createTime(now)
                .build();
        BeanHolder.get(DomainEventPublisher.class)
                .publishEvent(new UserRegisterSucceededEvent(userId, command.username(), now, command.ip()));
    }

    @Override
    public void handleLogin(UserLoginCommand command) {
        var now = OffsetDateTime.now();
        if (this.user == null) {
            var reason = "用户不存在";
            BeanHolder.get(DomainEventPublisher.class)
                    .publishEvent(new UserLoginFailedEvent(null, command.username(), reason, now, command.ip()));
            throw new DomainBusinessException(reason);
        }
        var encoder = BeanHolder.get(PasswordEncoder.class);
        if (!encoder.matches(command.password().value(), this.account.password().value())) {
            var reason = "密码不正确";
            BeanHolder.get(DomainEventPublisher.class)
                    .publishEvent(new UserLoginFailedEvent(this.user.id(), this.user.username(), reason, now, command.ip()));
            throw new DomainBusinessException(reason);
        }
        this.token = BeanHolder.get(UserRepo.class).createToken(this.user.id());
        BeanHolder.get(DomainEventPublisher.class)
                .publishEvent(new UserLoginSucceededEvent(this.user.id(), this.user.username(), now, command.ip()));
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
                .build();
        this.user = this.user.toBuilder()
                .nickname(command.nickname())
                .phone(command.phone())
                .build();
        BeanHolder.get(DomainEventPublisher.class)
                .publishEvent(new UserUpdateInfoSucceededEvent(this.user.id(), command.username(), now));
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
        this.token = BeanHolder.get(UserRepo.class).refreshToken(command.userId(), token);
        return this.token;
    }

    @Override
    public void handleUpdatePassword(UserUpdatePasswordCommand command) {
        var encoder = BeanHolder.get(PasswordEncoder.class);
        if (!encoder.matches(command.oldPassword().value(), this.account.password().value())) {
            throw new DomainBusinessException("旧密码不正确");
        }
        this.account = this.account.toBuilder()
                .password(new PasswordVo(encoder.encode(command.newPassword().value()), true))
                .build();
    }

    public boolean hasLogin() {
        return token != null && BeanHolder.get(PasswordEncoder.class).isValid(account.password().value());
    }
}
