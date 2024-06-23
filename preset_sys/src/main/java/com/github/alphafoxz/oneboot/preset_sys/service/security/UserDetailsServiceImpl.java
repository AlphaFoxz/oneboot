package com.github.alphafoxz.oneboot.preset_sys.service.security;

import com.github.alphafoxz.oneboot.preset_sys.service.security.bean.UserDetailsImpl;
import org.springframework.lang.Nullable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Nullable
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null) {
            return null;
        }
//        PsysUserPo userPo = psysUserCrud.selectOne(
//                Const.PSYS_USER.USERNAME.eq(username)
//        );
//        if (userPo == null) {
//            throw new OnebootAuthException("用户" + username + "不存在", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        PsysAccountPo accountPo = psysAccountCrud.selectOne(userPo.accountId());
//        if (accountPo == null) {
//            throw new OnebootDirtyDataException("用户账号对应不上，请检查是否为脏数据 username： " + username, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
        UserDetailsImpl userDetails = new UserDetailsImpl();
//        userDetails.setUsername(userPo.username());
//        userDetails.setPassword(userPo.password());
//        userDetails.setSubjectId(userPo.subjectId());
//        userDetails.setAccountNonExpired(!accountPo.expired() && !userPo.expired());
//        userDetails.setEnabled(accountPo.enabled() && userPo.enabled());
        //TODO 检查凭证是否过期
        userDetails.setCredentialsNonExpired(true);
        return userDetails;
    }
}
