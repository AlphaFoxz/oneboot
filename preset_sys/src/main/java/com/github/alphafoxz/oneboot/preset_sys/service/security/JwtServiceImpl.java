package com.github.alphafoxz.oneboot.preset_sys.service.security;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.lang.Snowflake;
import com.github.alphafoxz.oneboot.common.exceptions.OnebootApiDesignException;
import com.github.alphafoxz.oneboot.common.exceptions.OnebootAuthException;
import com.github.alphafoxz.oneboot.common.interfaces.security.JwtService;
import com.github.alphafoxz.oneboot.common.toolkit.coding.DateUtil;
import com.github.alphafoxz.oneboot.preset_sys.pojo.security.UserDetailsImpl;
import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.SignedJWT;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.KeyPair;

@Slf4j
@Service
public class JwtServiceImpl implements JwtService<UserDetails> {
    @Resource
    private Snowflake snowflake;
    private static final JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.HS256)
            .type(JOSEObjectType.JWT)
            .build();
    private JWSSigner jwsSigner;

    @Autowired
    private void init(KeyPair keyPair) {
        try {
            jwsSigner = new MACSigner(keyPair.getPrivate().getEncoded());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String token(@NonNull UserDetails payload) {
        DateTime now = DateUtil.date();
        String subjectId = null;
        if (payload instanceof UserDetailsImpl userDetails) {
            subjectId = userDetails.getSubjectId().toString();
        }
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .jwtID(snowflake.nextIdStr())
                .issueTime(now)
                .expirationTime(now.offsetNew(DateField.MINUTE, 30))
                .claim("username", payload.getUsername())
                .claim("password", payload.getPassword())
                .claim("authorities", payload.getAuthorities())
                .claim("enabled", payload.isEnabled())
                .subject(subjectId)
                .build();
        SignedJWT jwt = new SignedJWT(jwsHeader, jwtClaimsSet);
        try {
            jwt.sign(jwsSigner);
        } catch (Throwable t) {
            log.error("签名报错", t);
            throw new OnebootApiDesignException("签名失败，请检查功能", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return jwt.serialize();
    }

    @Override
    public UserDetails verify(@NonNull String token) {
        try {
            UserDetailsImpl result = new UserDetailsImpl();
            JWT jwt = JWTParser.parse(token);
            JWTClaimsSet jwtClaimsSet = jwt.getJWTClaimsSet();
            result.setSubjectId(Long.parseLong(jwtClaimsSet.getSubject()));
            result.setUsername(jwtClaimsSet.getStringClaim("username"));
            result.setPassword(jwtClaimsSet.getStringClaim("password"));
            result.setEnabled(jwtClaimsSet.getBooleanClaim("enabled"));
            return result;
        } catch (Exception e) {
            log.error("验证token失败", e);
        }
        throw new OnebootAuthException("验证token失败", HttpStatus.UNAUTHORIZED);
    }

    @Override
    public String refresh(@NonNull String token) {
        // TODO
        return null;
    }
}
