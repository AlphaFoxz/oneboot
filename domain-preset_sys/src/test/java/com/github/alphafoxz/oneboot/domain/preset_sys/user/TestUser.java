package com.github.alphafoxz.oneboot.domain.preset_sys.user;

import cn.hutool.core.lang.Assert;
import com.github.alphafoxz.oneboot.core.configuration.BeanHolder;
import com.github.alphafoxz.oneboot.core.domain.DomainEventPublisher;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.command.UserLoginCommand;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.IpVo;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.PasswordVo;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.TokenVo;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.UsernameVo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.OffsetDateTime;
import java.util.regex.Pattern;

@SuppressWarnings("NonAsciiCharacters")
public class TestUser {
    private final UserRepo userRepo = Mockito.mock(UserRepo.class);
    private final PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);
    private final DomainEventPublisher domainEventPublisher = Mockito.mock(DomainEventPublisher.class);

    {
        var encoder = new BCryptPasswordEncoder();
        var PATTERN = Pattern.compile("\\A\\$2([ayb])?\\$(\\d\\d)\\$[./0-9A-Za-z]{53}");

        Mockito.when(passwordEncoder.isValid(Const.encryptedPassword.value()))
                .thenReturn(PATTERN.matcher(Const.encryptedPassword.value()).matches());
        Mockito.when(passwordEncoder.matches(Const.rawPassword.value(), Const.encryptedPassword.value()))
                .thenReturn(encoder.matches(Const.rawPassword.value(), Const.encryptedPassword.value()));
        Mockito.when(userRepo.nextUserId()).thenReturn(2L);
        Mockito.when(userRepo.nextAccountId()).thenReturn(2L);
        Mockito.when(userRepo.createToken(1L)).thenReturn(new TokenVo(1L, "123", "123", OffsetDateTime.now()));
        Mockito.when(userRepo.findByUsername(new UsernameVo("admin"))).thenReturn(Const.adminUserAgg);

        BeanHolder.setMockBean(userRepo);
        BeanHolder.setMockBean(passwordEncoder);
        BeanHolder.setMockBean(domainEventPublisher);
    }

    @Test
    public void BCryptPasswordEncoder实现测试用户登录() {
        var agg = userRepo.findByUsername(new UsernameVo("admin"));
        agg.handleLogin(new UserLoginCommand(new UsernameVo("admin"), new PasswordVo("123456", false), new IpVo("127.0.0.1")));
        Assert.isTrue(agg.hasLogin());

        Mockito.verify(userRepo).createToken(1L);
    }
}
