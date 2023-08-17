package com.github.alphafoxz.oneboot.preset_sys.service.security;

import com.github.alphafoxz.oneboot.common.exceptions.OnebootAuthException;
import com.github.alphafoxz.oneboot.common.exceptions.OnebootDirtyDataException;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysHrAccountPo;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysHrUserPo;
import com.github.alphafoxz.oneboot.preset_sys.pojo.security.UserDetailsImpl;
import com.github.alphafoxz.oneboot.preset_sys.service.human_resources.crud.PsysHrAccountCrudService;
import com.github.alphafoxz.oneboot.preset_sys.service.human_resources.crud.PsysHrUserCrudService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.github.alphafoxz.oneboot.preset_sys.gen.jooq.Tables.PSYS_HR_USER;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private PsysHrUserCrudService psysHrUserCrudService;
    @Resource
    private PsysHrAccountCrudService psysHrAccountCrudService;

    @Nullable
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null) {
            return null;
        }
        PsysHrUserPo userPo = psysHrUserCrudService.selectOne(
                PSYS_HR_USER.USERNAME.eq(username)
        );
        if (userPo == null) {
            throw new OnebootAuthException("用户不存在 username： " + username, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PsysHrAccountPo accountPo = psysHrAccountCrudService.selectOne(userPo.accountId());
        if (accountPo == null) {
            throw new OnebootDirtyDataException("用户账号对应不上，请检查是否为脏数据 username： " + username, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        UserDetailsImpl userDetails = new UserDetailsImpl();
        userDetails.setUsername(userPo.username());
        userDetails.setPassword(userPo.password());
        userDetails.setSubjectId(userPo.subjectId());
        userDetails.setAccountNonExpired(!accountPo.expired() && !userPo.expired());
        userDetails.setEnabled(accountPo.enable() && userPo.enabled());
        //TODO 检查凭证是否过期
        userDetails.setCredentialsNonExpired(true);
        return userDetails;
    }
}
