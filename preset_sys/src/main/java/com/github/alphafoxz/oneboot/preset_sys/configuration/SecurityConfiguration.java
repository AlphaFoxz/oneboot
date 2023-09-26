package com.github.alphafoxz.oneboot.preset_sys.configuration;

import cn.hutool.crypto.asymmetric.AsymmetricAlgorithm;
import com.github.alphafoxz.oneboot.common.toolkit.coding.CollUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.ResourceUtil;
import com.github.alphafoxz.oneboot.preset_sys.PsysConstants;
import com.github.alphafoxz.oneboot.preset_sys.service.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain standardSecurityFilterChain(HttpSecurity http,
                                                           HandlerMappingIntrospector spector,
                                                           LogoutSuccessHandler logoutSuccessHandler,
                                                           JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(spector);
        http
                //访问规则
                .authorizeHttpRequests(requestMatcherRegistry -> requestMatcherRegistry
                        .requestMatchers(
                                mvcMatcherBuilder.pattern("/_sdk/**"),
                                mvcMatcherBuilder.pattern("/preset_sys/auth/login"),
                                mvcMatcherBuilder.pattern("/preset_sys/auth/logout"),
                                // swagger-ui
                                mvcMatcherBuilder.pattern(HttpMethod.GET, "/swagger-ui.html"),
                                mvcMatcherBuilder.pattern("/swagger-ui/**"),
                                mvcMatcherBuilder.pattern(HttpMethod.GET, "/v3/**")
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilter(jwtAuthenticationFilter)
                //退出登录
                .logout(configurer -> configurer.logoutUrl("/preset_sys/auth/logout").logoutSuccessHandler(logoutSuccessHandler))
                //禁用session
                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //配置跨域
                .cors(configurer -> configurer.configurationSource(corsConfigurationSource()))
                //禁用Basic认证（httpHeader里传用户名密码）
                .httpBasic(AbstractHttpConfigurer::disable)
                //采用了JWT方案而非cookie，所以可以禁用csrf防御
                .csrf(AbstractHttpConfigurer::disable)
                //前后分离，所以禁用后端登录表单
                .formLogin(AbstractHttpConfigurer::disable);
        return http.build();
    }

    /**
     * 跨域配置
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(CollUtil.newArrayList("*"));
        configuration.setAllowedMethods(CollUtil.newArrayList(HttpMethod.POST.name(), HttpMethod.GET.name()));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    /**
     * 密码加密（加盐）
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 证书
     */
    @Bean
    public KeyPair rootKeyPair() throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance(AsymmetricAlgorithm.RSA.name());
        PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(ResourceUtil.readBytes(PsysConstants.ROOT_RSA_PRIVATE_PATH));
        PrivateKey priKey = keyFactory.generatePrivate(priPKCS8);
        X509EncodedKeySpec pubX509 = new X509EncodedKeySpec(ResourceUtil.readBytes(PsysConstants.ROOT_RSA_PUBLIC_PATH));
        PublicKey pubKey = keyFactory.generatePublic(pubX509);
        return new KeyPair(pubKey, priKey);
    }
}
