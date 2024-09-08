package com.github.alphafoxz.oneboot.domain.preset_sys.user;

import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.TokenVo;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.UsernameVo;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

public interface UserRepo {
    @Nonnull
    UserAgg findBySubjectId(UsernameVo username);

    @Nonnull
    UserAgg findById(Long userId);

    @Nonnull
    UserAgg findByUsername(UsernameVo username);

    @Nonnull
    TokenVo createToken(Long userId);

    @Nullable
    TokenVo refreshToken(Long userId, TokenVo oldToken);

    void save(UserAgg user);

    @Nonnull
    Long nextUserId();

    @Nonnull
    Long nextAccountId();


}

