package com.github.alphafoxz.oneboot.preset_sys.service.security;

import com.github.alphafoxz.oneboot.core.exceptions.OnebootAuthException;
import com.github.alphafoxz.oneboot.core.toolkit.coding.StrUtil;
import com.github.alphafoxz.oneboot.preset_sys.service.security.bean.UserDetailsImpl;
import com.github.alphafoxz.oneboot.preset_sys.service.security.toolkit.JwtUtil;
import com.nimbusds.jwt.SignedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String tokenStr = request.getHeader(HttpHeaders.AUTHORIZATION);
        // 这里如果没有jwt，继续往后走，因为后面还有鉴权管理器等去判断是否拥有身份凭证，所以是可以放行的
        // 没有jwt相当于匿名访问，若有一些接口是需要权限的，则不能访问这些接口
        if (StrUtil.isBlankOrUndefined(tokenStr)) {
            chain.doFilter(request, response);
            return;
        }
        if (tokenStr.startsWith("Bearer ")) {
            tokenStr = tokenStr.replaceFirst("Bearer ", "");
        }
        SignedJWT verifiedJwt = JwtUtil.toVerifiedJwt(tokenStr);
        if (verifiedJwt == null) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "token无效，请重新登录");
            response.flushBuffer();
            throw new OnebootAuthException("token无效，请重新登录", HttpStatus.UNAUTHORIZED);
        }
        if (JwtUtil.isTokenExpired(verifiedJwt)) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "token已过期，请重新登录");
            response.flushBuffer();
            throw new OnebootAuthException("token已过期，请重新登录", HttpStatus.UNAUTHORIZED);
        }
        UserDetailsImpl userDetails = JwtUtil.parseUserDetails(verifiedJwt);
        if (userDetails == null) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "token信息异常，请重新登录");
            response.flushBuffer();
            throw new OnebootAuthException("token信息异常，请重新登录", HttpStatus.UNAUTHORIZED);
        }
        Long subjectId = userDetails.getSubjectId();
        // 获取用户的权限等信息
        // 构建UsernamePasswordAuthenticationToken,这里密码为null，是因为提供了正确的JWT,实现自动登录
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(subjectId, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);

        chain.doFilter(request, response);
    }
}
