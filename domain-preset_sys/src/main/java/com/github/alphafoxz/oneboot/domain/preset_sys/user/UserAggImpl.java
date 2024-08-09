package com.github.alphafoxz.oneboot.domain.preset_sys.user;

import com.github.alphafoxz.oneboot.domain.preset_sys.user.entity.Account;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.vo.Token;
import com.github.alphafoxz.oneboot.domain.preset_sys.user.entity.User;

/**
 * 用户聚合
 * @author Wong
 * @version 1.0
 * @created 03-7月-2024 7:16:46
 */
@lombok.experimental.Accessors(chain = true)
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.Getter
public class UserAggImpl implements UserAgg {

    private Account account;
    private Token token;
    private User user;

    public boolean isLogin(){
    	return false;
    }
}//end UserAggImpl