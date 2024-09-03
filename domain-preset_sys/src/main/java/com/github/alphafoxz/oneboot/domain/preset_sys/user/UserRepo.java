package com.github.alphafoxz.oneboot.domain.preset_sys.user;

import com.github.alphafoxz.oneboot.core.toolkit.coding.SpringUtil;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.TokenVo;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.UsernameVo;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

public interface UserRepo {
    public static UserRepo getInstance() {
        if (Instance.VALUE == null) {
            Instance.VALUE = SpringUtil.getBean(UserRepo.class);
        }
        return Instance.VALUE;
    }

    class Instance {
        private static UserRepo VALUE = null;
    }

    public static void setInstance(UserRepo instance) {
        if (Instance.VALUE != null) {
            throw new RuntimeException("Instance already exists");
        }
        Instance.VALUE = instance;
    }

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

