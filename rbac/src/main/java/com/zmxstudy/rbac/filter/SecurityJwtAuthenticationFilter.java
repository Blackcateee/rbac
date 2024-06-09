package com.zmxstudy.rbac.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zmxstudy.rbac.config.SecurityConfig;
import com.zmxstudy.rbac.constant.SecurityConstant;
import com.zmxstudy.rbac.util.JwtUtil;
import com.zmxstudy.rbac.util.RedisUtil;
import com.zmxstudy.rbac.util.ResponseUtil;
import com.zmxstudy.rbac.vo.Result;
import com.zmxstudy.rbac.vo.ResultCode;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.io.IOException;
import java.util.Arrays;

/**
 * Jwt 权限认证过滤器
 *
 * @author star
 */
@Component
@AllArgsConstructor
public class SecurityJwtAuthenticationFilter implements Filter {
    private JwtUtil jwtUtil;
    private ObjectMapper objectMapper;
    private RedisUtil redisUtil;
    private AntPathMatcher antPathMatcher;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 1、如果是登录页则跳过
        if (SecurityConfig.LOGIN_URI.equalsIgnoreCase(request.getRequestURI()) ||
                Arrays.stream(SecurityConfig.WHITE_LIST).anyMatch(white -> {
                    return antPathMatcher.match(white, request.getRequestURI());
                })
        ) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        // 2、在jwt认证之前有没有其他方式认证过
        Authentication otherAuthentication = SecurityContextHolder.getContext().getAuthentication();
        if (otherAuthentication != null && otherAuthentication.isAuthenticated()) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        // 3、获取请求头中的jwt
        String jwt = request.getHeader(SecurityConstant.TOKEN_HEADER);
        try {
            // 4、获取redis中存储的信息
            Authentication authentication = (Authentication) redisUtil.hashGet(SecurityConstant.TOKEN_KEY, jwtUtil.parse(jwt).getSubject());
            if (authentication == null) {
                ResponseUtil.writeJson(response, objectMapper.writeValueAsString(Result.error(ResultCode.CLIENT_A0311)));
                return;
            }
            // 5、构建出Authentication对象，并且放到SecurityContext，表示认证成功
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            ResponseUtil.writeJson(response, objectMapper.writeValueAsString(Result.error(ResultCode.CLIENT_A0304)));
        }
    }
}
