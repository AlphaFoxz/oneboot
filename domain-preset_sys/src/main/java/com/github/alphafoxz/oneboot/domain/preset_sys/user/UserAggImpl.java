package com.github.alphafoxz.oneboot.domain.preset_sys.user;

import com.github.alphafoxz.oneboot.domain.preset_sys.user.entity.User;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.Account;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.Token;
import lombok.AccessLevel;

/**
 * 用户聚合
 *
 * @author Wong
 * @version 1.0
 * @created 22-6月-2024 1:59:38
 */
@lombok.experimental.Accessors(chain = true)
@lombok.Setter(AccessLevel.PUBLIC)
@lombok.NoArgsConstructor(access = lombok.AccessLevel.PUBLIC)
@lombok.Getter(lombok.AccessLevel.PUBLIC)
public class UserAggImpl implements UserAgg {

    private Account account;
    private Token token;
    private User user;

    public boolean isLogin() {
        return false;
    }
}//end UserAggImpl