package com.github.alphafoxz.oneboot.preset_sys.service.security;

import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysHrUserPo;
import com.github.alphafoxz.oneboot.preset_sys.pojo.security.UserDetailsImpl;
import com.github.alphafoxz.oneboot.preset_sys.service.human_resources.crud.PsysHrUserCrudService;
import jakarta.annotation.Resource;
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

    @Nullable
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null) {
            return null;
        }
        PsysHrUserPo psysHrUserPo = psysHrUserCrudService.selectOne(
                PSYS_HR_USER.USERNAME.eq(username)
        );
        if (psysHrUserPo == null) {
            return null;
        }
        //TODO 需要设计为单账号多用户
        UserDetailsImpl userDetails = new UserDetailsImpl();
        userDetails.setUsername(psysHrUserPo.username());
        userDetails.setPassword(psysHrUserPo.password());
        return null;
    }
}
