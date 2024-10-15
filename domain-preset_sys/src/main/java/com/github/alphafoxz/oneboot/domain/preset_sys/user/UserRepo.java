package com.github.alphafoxz.oneboot.domain.preset_sys.user;

import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.TokenVo;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.UsernameVo;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public interface UserRepo {
    @NonNull
    UserAgg findBySubjectId(UsernameVo username);

    @NonNull
    UserAgg findById(Long userId);

    @NonNull
    UserAgg findByUsername(UsernameVo username);

    @NonNull
    TokenVo createToken(Long userId);

    @Nullable
    TokenVo refreshToken(Long userId, TokenVo oldToken);

    void save(UserAgg user);

    @NonNull
    Long nextUserId();

    @NonNull
    Long nextAccountId();

}

