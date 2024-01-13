package com.github.alphafoxz.oneboot.preset_sys.service.security;

import com.github.alphafoxz.oneboot.common.exceptions.OnebootAuthException;
import com.github.alphafoxz.oneboot.common.exceptions.OnebootDirtyDataException;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysAccountPo;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysUserPo;
import com.github.alphafoxz.oneboot.preset_sys.service.crud.PsysAccountCrud;
import com.github.alphafoxz.oneboot.preset_sys.service.crud.PsysUserCrud;
import com.github.alphafoxz.oneboot.preset_sys.service.security.bean.UserDetailsImpl;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private PsysAccountCrud psysAccountCrud;
    @Resource
    private PsysUserCrud psysUserCrud;

    @Nullable
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null) {
            return null;
        }
        PsysUserPo userPo = psysUserCrud.selectOne(
                C.PSYS_USER.USERNAME.eq(username)
        );
        if (userPo == null) {
            throw new OnebootAuthException("用户" + username + "不存在", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PsysAccountPo accountPo = psysAccountCrud.selectOne(userPo.accountId());
        if (accountPo == null) {
            throw new OnebootDirtyDataException("用户账号对应不上，请检查是否为脏数据 username： " + username, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        UserDetailsImpl userDetails = new UserDetailsImpl();
        userDetails.setUsername(userPo.username());
        userDetails.setPassword(userPo.password());
        userDetails.setSubjectId(userPo.subjectId());
        userDetails.setAccountNonExpired(!accountPo.expired() && !userPo.expired());
        userDetails.setEnabled(accountPo.enabled() && userPo.enabled());
        //TODO 检查凭证是否过期
        userDetails.setCredentialsNonExpired(true);
        return userDetails;
    }
}
