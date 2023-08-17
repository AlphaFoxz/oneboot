package com.github.alphafoxz.oneboot.preset_sys.configuration;

import cn.hutool.crypto.asymmetric.AsymmetricAlgorithm;
import com.github.alphafoxz.oneboot.common.toolkit.coding.CollUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.SecureUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.security.KeyPair;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain standardSecurityFilterChain(HttpSecurity http, HandlerMappingIntrospector spector) throws Exception {
        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(spector);
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(
                                mvcMatcherBuilder.pattern("/_sdk/**"),
                                // swagger-ui
                                mvcMatcherBuilder.pattern(HttpMethod.GET, "/swagger-ui.html"),
                                mvcMatcherBuilder.pattern("/swagger-ui/**"),
                                mvcMatcherBuilder.pattern(HttpMethod.GET, "/v3/**"),
                                mvcMatcherBuilder.pattern("/preset_sys/login/**")
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .cors(corsConfigurer -> {
                    corsConfigurer.configurationSource(corsConfigurationSource());
                })
                .httpBasic(withDefaults())
                .csrf(httpSecurityCsrfConfigurer -> {
                    httpSecurityCsrfConfigurer.ignoringRequestMatchers(
                            mvcMatcherBuilder.pattern("/_sdk/**"),
                            mvcMatcherBuilder.pattern("/preset_sys/**")
                    );
                })
                .formLogin(withDefaults());
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(CollUtil.newArrayList("*"));
        configuration.setAllowedMethods(CollUtil.newArrayList(HttpMethod.POST.name(), HttpMethod.GET.name()));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public KeyPair keypair() {
        return SecureUtil.generateKeyPair(AsymmetricAlgorithm.RSA.name());
    }
}
