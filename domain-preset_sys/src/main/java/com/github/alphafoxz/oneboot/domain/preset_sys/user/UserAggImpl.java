package com.github.alphafoxz.oneboot.domain.preset_sys.user;

import com.github.alphafoxz.oneboot.domain.preset_sys.DomainEventPublisher;
import com.github.alphafoxz.oneboot.domain.preset_sys.DomainException;
import com.github.alphafoxz.oneboot.domain.preset_sys.PasswordEncoder;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.command.*;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.entity.Account;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.entity.User;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.event.*;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.PasswordVo;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.TokenVo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

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
            DomainEventPublisher.getInstance().publishEvent(new UserRegisterFailedEvent(command.ip(), reason, now));
            throw new DomainException(reason, HttpStatus.BAD_REQUEST);
        } else if (account != null) {
            var reason = "该账号已存在（目前不支持一个账号，多个用户）";
            DomainEventPublisher.getInstance().publishEvent(new UserRegisterFailedEvent(command.ip(), reason, now));
            throw new DomainException("", HttpStatus.BAD_REQUEST);
        }
        var encoder = PasswordEncoder.getInstance();
        var userId = UserRepo.getInstance().nextUserId();
        var accountId = UserRepo.getInstance().nextAccountId();
        var encryptedPassword = new PasswordVo(encoder.encode(command.password().value()), true);
        this.account = new Account()
                .setId(accountId)
                .setPassword(encryptedPassword)
                .setEmail(command.email())
                .setPhone(command.phone())
                .setCreateTime(now)
                .setUpdateTime(now);
        this.user = new User()
                .setId(userId)
                .setUsername(command.username())
                .setCreateTime(now)
                .setUpdateTime(now);
        DomainEventPublisher.getInstance()
                .publishEvent(new UserRegisterSucceededEvent(userId, command.username(), now, command.ip()));
    }

    @Override
    public void handleLogin(UserLoginCommand command) {
        var now = OffsetDateTime.now();
        if (this.user == null) {
            var reason = "用户不存在";
            DomainEventPublisher.getInstance().publishEvent(new UserLoginFailedEvent(null, command.username(), reason, now, command.ip()));
            throw new DomainException(reason, HttpStatus.FORBIDDEN);
        }
        var encoder = PasswordEncoder.getInstance();
        if (!encoder.matches(command.password().value(), this.account.getPassword().value())) {
            var reason = "密码不正确";
            DomainEventPublisher.getInstance().publishEvent(new UserLoginFailedEvent(this.user.getId(), this.user.getUsername(), reason, now, command.ip()));
            throw new DomainException(reason, HttpStatus.BAD_REQUEST);
        }
        this.token = UserRepo.getInstance().createToken(this.user.getId());
        DomainEventPublisher.getInstance().publishEvent(new UserLoginSucceededEvent(this.user.getId(), this.user.getUsername(), now, command.ip()));
    }

    @Override
    public void handleUpdateInfo(UserUpdateInfoCommand command) {
        if (this.user == null) {
            throw new DomainException("用户不存在", HttpStatus.FORBIDDEN);
        }
        var now = OffsetDateTime.now();
        this.account.setEmail(command.email())
                .setPhone(command.phone())
                .setUpdateTime(now);
        this.user
                .setNickname(command.nickname())
                .setPhone(command.phone())
                .setUpdateTime(now);
        DomainEventPublisher.getInstance().publishEvent(new UserUpdateInfoSucceededEvent(this.user.getId(), command.username(), now));
    }

    @Override
    public void handleLogout(UserLogoutCommand command) {
        this.token = null;
    }

    @Override
    public TokenVo handleRefreshToken(UserRefreshTokenCommand command) {
        if (user == null) {
            throw new DomainException("用户不存在", HttpStatus.FORBIDDEN);
        } else if (this.token == null) {
            throw new DomainException("用户未登录", HttpStatus.UNAUTHORIZED);
        }
        this.token = UserRepo.getInstance().refreshToken(command.userId(), token);
        return this.token;
    }

    @Override
    public void handleUpdatePassword(UserUpdatePasswordCommand command) {
        var encoder = PasswordEncoder.getInstance();
        if (!encoder.matches(command.oldPassword().value(), this.account.getPassword().value())) {
            throw new DomainException("旧密码不正确", HttpStatus.BAD_REQUEST);
        }
        this.account
                .setPassword(new PasswordVo(encoder.encode(command.newPassword().value()), true))
                .setUpdateTime(OffsetDateTime.now());
    }
}