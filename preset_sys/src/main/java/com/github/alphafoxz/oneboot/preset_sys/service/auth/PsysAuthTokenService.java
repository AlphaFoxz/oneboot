package com.github.alphafoxz.oneboot.preset_sys.service.auth;

import com.github.alphafoxz.oneboot.common.exceptions.OnebootAuthException;
import com.github.alphafoxz.oneboot.common.exceptions.OnebootDirtyDataException;
import com.github.alphafoxz.oneboot.common.toolkit.coding.CollUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.DateUtil;
import com.github.alphafoxz.oneboot.common.toolkit.container.tuple.Tuple3;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysAuthAccountPo;
import com.github.alphafoxz.oneboot.preset_sys.gen.jooq.tables.pojos.PsysAuthUserPo;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysAuthTokenInfoDto;
import com.github.alphafoxz.oneboot.preset_sys.service.auth.crud.PsysAuthAccountCrud;
import com.github.alphafoxz.oneboot.preset_sys.service.auth.crud.PsysAuthUserCrud;
import com.github.alphafoxz.oneboot.preset_sys.service.security.bean.UserDetailsImpl;
import com.github.alphafoxz.oneboot.preset_sys.service.security.toolkit.JwtUtil;
import com.nimbusds.jwt.SignedJWT;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.github.alphafoxz.oneboot.preset_sys.gen.jooq.Tables.PSYS_AUTH_USER;

@Service
public class PsysAuthTokenService {
    @Resource
    private PsysAuthUserCrud psysAuthUserCrud;
    @Resource
    private PsysAuthAccountCrud psysAuthAccountCrud;
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private PasswordEncoder passwordEncoder;

    @NonNull
    public PsysAuthTokenInfoDto getAccessTokenAndRefreshToken(@NonNull String username, @NonNull String password) {
        PsysAuthUserPo psysAuthUserPo = psysAuthUserCrud.selectOne(
                PSYS_AUTH_USER.USERNAME.eq(username)
        );
        if (psysAuthUserPo == null) {
            throw new OnebootAuthException("用户不存在或密码错误", HttpStatus.BAD_REQUEST);
        }
        checkUserOrThrowsException(psysAuthUserPo);
        //TODO 验证密码、查询token、缓存、或者创建token
        boolean matches = passwordEncoder.matches(password, psysAuthUserPo.password());
        if (!matches) {
            throw new OnebootAuthException("用户不存在或密码错误", HttpStatus.BAD_REQUEST);
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        Tuple3<String, String, Date> tokens;
        if (userDetails instanceof UserDetailsImpl userDetailsImpl) {
            tokens = JwtUtil.genTokensAndExpireTime(userDetailsImpl);
        } else {
            tokens = JwtUtil.genTokensAndExpireTime(UserDetailsImpl.from(userDetails));
        }
        PsysAuthTokenInfoDto dto = new PsysAuthTokenInfoDto();
        dto.setUsername(userDetails.getUsername());
//        dto.setRoles(CollUtil.newArrayList(userDetails.getAuthorities()).stream().map(s -> s.toString()).collect(Collectors.toList()));
        //TODO 权限
        dto.setRoles(CollUtil.newArrayList("admin"));
        dto.setExpires(DateUtil.format(tokens.getIndex2(), "yyyy/MM/dd HH:mm:ss"));
        dto.setAccessToken(tokens.getIndex0());
        dto.setRefreshToken(tokens.getIndex1());
        return dto;
    }

    public void checkUserOrThrowsException(@Nullable PsysAuthUserPo psysAuthUserPo) throws RuntimeException {
        if (psysAuthUserPo == null) {
            throw new OnebootAuthException("用户不存在或密码错误", HttpStatus.BAD_REQUEST);
        }
        PsysAuthAccountPo accountPo = psysAuthAccountCrud.selectOne(psysAuthUserPo.accountId());
        if (accountPo == null) {
            throw new OnebootDirtyDataException("账号数据异常，请联系联系管理员解决", HttpStatus.FORBIDDEN);
        }
        if (!psysAuthUserPo.enabled() || !accountPo.enabled()) {
            throw new OnebootDirtyDataException("账号已停用，请联系联系管理员解决", HttpStatus.FORBIDDEN);
        }
        if (psysAuthUserPo.expired() || accountPo.expired()) {
            throw new OnebootDirtyDataException("账号已过期，请联系联系管理员解决", HttpStatus.FORBIDDEN);
        }
    }

    public PsysAuthTokenInfoDto refreshTokenByOld(PsysAuthTokenInfoDto userInfoDto) {
        SignedJWT refreshToken = JwtUtil.toVerifiedJwt(userInfoDto.getRefreshToken());
        if (refreshToken == null || JwtUtil.isTokenExpired(refreshToken)) {
            throw new OnebootAuthException("验证失败，请重新登录", HttpStatus.UNAUTHORIZED);
        }
        Tuple3<String, String, Date> newTokens = JwtUtil.genNewTokensAndExpireTimeByRefreshToken(userInfoDto.getAccessToken());
        UserDetailsImpl userDetails = JwtUtil.parseUserDetails(refreshToken);
        if (newTokens == null || userDetails == null) {
            throw new OnebootAuthException("验证失败，请重新登录", HttpStatus.UNAUTHORIZED);
        }
        PsysAuthTokenInfoDto dto = new PsysAuthTokenInfoDto();
        dto.setUsername(userDetails.getUsername());
//        dto.setRoles(CollUtil.newArrayList(userDetails.getAuthorities()).stream().map(s -> s.toString()).collect(Collectors.toList()));
        //TODO 权限
        dto.setRoles(CollUtil.newArrayList("admin"));
        dto.setExpires(DateUtil.format(newTokens.getIndex2(), "yyyy/MM/dd HH:mm:ss"));
        dto.setAccessToken(newTokens.getIndex0());
        dto.setRefreshToken(newTokens.getIndex1());
        return dto;
    }

    public void logout() {
        //TODO
    }
}
