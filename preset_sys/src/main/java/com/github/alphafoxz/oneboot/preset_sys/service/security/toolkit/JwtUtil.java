package com.github.alphafoxz.oneboot.preset_sys.service.security.toolkit;

import cn.hutool.core.lang.Snowflake;
import com.github.alphafoxz.oneboot.common.exceptions.OnebootApiDesignException;
import com.github.alphafoxz.oneboot.common.toolkit.coding.*;
import com.github.alphafoxz.oneboot.common.toolkit.container.tuple.Tuple2;
import com.github.alphafoxz.oneboot.common.toolkit.container.tuple.Tuples;
import com.github.alphafoxz.oneboot.preset_sys.PsysConstants;
import com.github.alphafoxz.oneboot.preset_sys.configuration.PsysProperties;
import com.github.alphafoxz.oneboot.preset_sys.gen.restful.dtos.PsysAuthTokenInfoDto;
import com.github.alphafoxz.oneboot.preset_sys.service.security.bean.UserDetailsImpl;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.time.Instant;
import java.util.Date;

@Slf4j
public final class JwtUtil {
    private static final JWSHeader JWS_HEADER = new JWSHeader.Builder(JWSAlgorithm.HS256)
            .type(JOSEObjectType.JWT)
            .build();
    private static volatile PsysProperties.JwtProperties jwtProperties;
    private static volatile Snowflake snowflake;
    private static JWSSigner jwsSigner;
    private static JWSVerifier jwsVerifier;
    private static String secret;

    private static Snowflake getSnowFlake() {
        if (snowflake == null) {
            snowflake = SpringUtil.getBean(Snowflake.class);
        }
        return snowflake;
    }

    private static String getSecret() {
        if (secret == null) {
            secret = SecureUtil.md5(ResourceUtil.readUtf8Str(PsysConstants.ROOT_RSA_PRIVATE_PATH));
        }
        return secret;
    }

    private static JWSVerifier getJwsVerifier() throws JOSEException {
        if (jwsVerifier == null) {
            jwsVerifier = new MACVerifier(getSecret());
        }
        return jwsVerifier;
    }

    private static JWSSigner getJwsSigner() throws KeyLengthException {
        if (jwsSigner == null) {
            jwsSigner = new MACSigner(getSecret().getBytes(StandardCharsets.UTF_8));
        }
        return jwsSigner;
    }

    private static PsysProperties.JwtProperties getJwtProperties() {
        if (jwtProperties == null) {
            jwtProperties = SpringUtil.getBean(PsysProperties.class).getJwt();
        }
        return jwtProperties;
    }

    public static PsysAuthTokenInfoDto genAuthUserInfo(@NonNull UserDetailsImpl userDetails) {
        PsysAuthTokenInfoDto dto = new PsysAuthTokenInfoDto();
        Date now = Date.from(Instant.now());
        Instant accessTokenInstant = Instant.now();
        Instant refreshTokenInstant = Instant.now();
        accessTokenInstant = accessTokenInstant.plus(getJwtProperties().getAccessTokenExpireTime(), getJwtProperties().getAccessTokenExpireTimeUnit().toChronoUnit());
        refreshTokenInstant = refreshTokenInstant.plus(getJwtProperties().getRefreshTokenExpireTime(), getJwtProperties().getRefreshTokenExpireTimeUnit().toChronoUnit());
        String subjectId = userDetails.getSubjectId().toString();
        JWTClaimsSet accessClaimsSet = new JWTClaimsSet.Builder()
                .jwtID(getSnowFlake().nextIdStr())
                .issueTime(now)
                .issuer("SYSTEM")
                .expirationTime(Date.from(accessTokenInstant))
                .claim("username", userDetails.getUsername())
                .claim("password", userDetails.getPassword())
                .claim("authorities", userDetails.getAuthorities())
                .claim("enabled", userDetails.isEnabled())
                .claim("accountNonExpired", userDetails.isAccountNonExpired())
                .claim("accountNonLocked", userDetails.isAccountNonLocked())
                .claim("credentialsNonExpired", userDetails.isCredentialsNonExpired())
                .subject(subjectId)
                .build();
        JWTClaimsSet refreshClaimsSet = new JWTClaimsSet.Builder()
                .jwtID(getSnowFlake().nextIdStr())
                .issueTime(now)
                .issuer("SYSTEM")
                .expirationTime(Date.from(refreshTokenInstant))
                .claim("username", userDetails.getUsername())
                .claim("password", userDetails.getPassword())
                .claim("authorities", userDetails.getAuthorities())
                .claim("enabled", userDetails.isEnabled())
                .claim("accountNonExpired", userDetails.isAccountNonExpired())
                .claim("accountNonLocked", userDetails.isAccountNonLocked())
                .claim("credentialsNonExpired", userDetails.isCredentialsNonExpired())
                .subject(subjectId)
                .build();
        SignedJWT accessToken = new SignedJWT(JWS_HEADER, accessClaimsSet);
        SignedJWT refreshToken = new SignedJWT(JWS_HEADER, refreshClaimsSet);
        try {
            accessToken.sign(getJwsSigner());
            refreshToken.sign(getJwsSigner());
        } catch (Throwable t) {
            log.error("签名报错", t);
            throw new OnebootApiDesignException("签名失败，请检查功能", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        dto.setUsername(userDetails.getUsername());
//        dto.setRoles(CollUtil.newArrayList(userDetails.getAuthorities()).stream().map(s -> s.toString()).collect(Collectors.toList()));
        //TODO 权限
        dto.setRoles(CollUtil.newArrayList("admin"));
        dto.setExpires(DateUtil.format(Date.from(accessTokenInstant), "yyyy/MM/dd HH:mm:ss"));
        dto.setAccessToken(accessToken.serialize());
        dto.setRefreshToken(refreshToken.serialize());
        return dto;
    }

    public static Tuple2<String, String> genAccessTokenAndRefreshToken(@NonNull UserDetailsImpl userDetails) {
        Date now = Date.from(Instant.now());
        Instant accessTokenInstant = Instant.now();
        Instant refreshTokenInstant = Instant.now();
        accessTokenInstant = accessTokenInstant.plus(getJwtProperties().getAccessTokenExpireTime(), getJwtProperties().getAccessTokenExpireTimeUnit().toChronoUnit());
        refreshTokenInstant = refreshTokenInstant.plus(getJwtProperties().getRefreshTokenExpireTime(), getJwtProperties().getRefreshTokenExpireTimeUnit().toChronoUnit());
        String subjectId = userDetails.getSubjectId().toString();
        JWTClaimsSet accessClaimsSet = new JWTClaimsSet.Builder()
                .jwtID(getSnowFlake().nextIdStr())
                .issueTime(now)
                .issuer("SYSTEM")
                .expirationTime(Date.from(accessTokenInstant))
                .claim("username", userDetails.getUsername())
                .claim("password", userDetails.getPassword())
                .claim("authorities", userDetails.getAuthorities())
                .claim("enabled", userDetails.isEnabled())
                .claim("accountNonExpired", userDetails.isAccountNonExpired())
                .claim("accountNonLocked", userDetails.isAccountNonLocked())
                .claim("credentialsNonExpired", userDetails.isCredentialsNonExpired())
                .subject(subjectId)
                .build();
        JWTClaimsSet refreshClaimsSet = new JWTClaimsSet.Builder()
                .jwtID(getSnowFlake().nextIdStr())
                .issueTime(now)
                .issuer("SYSTEM")
                .expirationTime(Date.from(refreshTokenInstant))
                .claim("username", userDetails.getUsername())
                .claim("password", userDetails.getPassword())
                .claim("authorities", userDetails.getAuthorities())
                .claim("enabled", userDetails.isEnabled())
                .claim("accountNonExpired", userDetails.isAccountNonExpired())
                .claim("accountNonLocked", userDetails.isAccountNonLocked())
                .claim("credentialsNonExpired", userDetails.isCredentialsNonExpired())
                .subject(subjectId)
                .build();
        SignedJWT accessToken = new SignedJWT(JWS_HEADER, accessClaimsSet);
        SignedJWT refreshToken = new SignedJWT(JWS_HEADER, refreshClaimsSet);
        try {
            accessToken.sign(getJwsSigner());
            refreshToken.sign(getJwsSigner());
        } catch (Throwable t) {
            log.error("签名报错", t);
            throw new OnebootApiDesignException("签名失败，请检查功能", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return Tuples.of(accessToken.serialize(), refreshToken.serialize());
    }

    public static Tuple2<String, String> genNewTokenByRefreshToken(String refreshTokenStr) {
        SignedJWT verifiedJwt = toVerifiedJwt(refreshTokenStr);
        if (verifiedJwt == null || isTokenExpired(verifiedJwt)) {
            return null;
        }
        UserDetailsImpl userDetails = parseUserDetails(verifiedJwt);
        return userDetails == null ? null : genAccessTokenAndRefreshToken(userDetails);
    }

    public static boolean verify(String tokenStr) {
        SignedJWT verifiedJwt = toVerifiedJwt(tokenStr);
        return verifiedJwt != null;
    }

    @Nullable
    public static SignedJWT toVerifiedJwt(String tokenStr) {
        try {
            SignedJWT jws = SignedJWT.parse(tokenStr);
            boolean verify = jws.verify(getJwsVerifier());
            if (verify) {
                return jws;
            }
            log.error("验证token失败");
        } catch (Exception e) {
            log.error("验证token异常", e);
        }
        return null;
    }

    /**
     * token是否过期
     */
    public static boolean isTokenExpired(@NonNull SignedJWT verifiedJwt) {
        try {
            JWTClaimsSet jwtClaimsSet = verifiedJwt.getJWTClaimsSet();
            return !jwtClaimsSet.getExpirationTime().before(Date.from(Instant.now()));
        } catch (ParseException e) {
            log.error("解析token异常", e);
            return false;
        }
    }

    public static boolean isTokenExpired(String tokenStr) {
        SignedJWT verifiedJwt = toVerifiedJwt(tokenStr);
        return verifiedJwt == null || isTokenExpired(verifiedJwt);
    }

    /**
     * 解析token中的用户信息
     */
    @Nullable
    public static UserDetailsImpl parseUserDetails(@NonNull SignedJWT verifiedJwt) {
        try {
            JWTClaimsSet jwtClaimsSet = verifiedJwt.getJWTClaimsSet();
            if (jwtClaimsSet == null) {
                log.warn("获取payload失败");
                return null;
            }
            return UserDetailsImpl.from(jwtClaimsSet);
        } catch (ParseException e) {
            log.error("解析claims异常", e);
            return null;
        }
    }

    public static UserDetailsImpl parseUserDetails(@Nullable String tokenStr) {
        if (StrUtil.isBlank(tokenStr)) {
            return null;
        }
        SignedJWT verifiedJwt = toVerifiedJwt(tokenStr);
        if (verifiedJwt == null) {
            return null;
        }
        return parseUserDetails(verifiedJwt);
    }
}
