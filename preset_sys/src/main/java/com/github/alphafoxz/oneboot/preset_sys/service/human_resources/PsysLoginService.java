package com.github.alphafoxz.oneboot.preset_sys.service.human_resources;

import com.github.alphafoxz.oneboot.common.exceptions.OnebootAuthException;
import com.github.alphafoxz.oneboot.common.interfaces.security.JwtService;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysHrUserPo;
import com.github.alphafoxz.oneboot.preset_sys.service.human_resources.crud.PsysHrUserCrudService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import static com.github.alphafoxz.oneboot.preset_sys.gen.jooq.Tables.PSYS_HR_USER;

@Service
public class PsysLoginService {
    @Resource
    private JwtService<UserDetails> jwtService;
    @Resource
    private PsysHrUserCrudService psysHrUserCrudService;
    @Resource
    private UserDetailsService userDetailsService;

    @Nullable
    public String queryOrGenToken(@NonNull String username, @NonNull String password) {
        PsysHrUserPo psysHrUserPo = psysHrUserCrudService.selectOne(
                PSYS_HR_USER.USERNAME.eq(username)
        );
        if (psysHrUserPo == null) {
            throw new OnebootAuthException("用户不存在或密码错误", HttpStatus.BAD_REQUEST);
        }
        //TODO 验证密码、查询token、缓存、或者创建token
        return jwtService.token(userDetailsService.loadUserByUsername(username));
    }
}
