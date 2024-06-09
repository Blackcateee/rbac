package com.zmxstudy.rbac.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zmxstudy.rbac.constant.SecurityConstant;
import com.zmxstudy.rbac.util.JwtUtil;
import com.zmxstudy.rbac.util.RedisUtil;
import com.zmxstudy.rbac.util.ResponseUtil;
import com.zmxstudy.rbac.vo.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 登录成功处理器
 *
 * @author star
 */
@Component
@AllArgsConstructor
public class SecurityAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private JwtUtil jwtUtil;
    private RedisUtil redisUtil;
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        redisUtil.hashSet(SecurityConstant.TOKEN_KEY, authentication.getName(), authentication, SecurityConstant.TOKEN_EXPIRE);

        ResponseUtil.writeJson(response, objectMapper.writeValueAsString(Result.ok(jwtUtil.create(authentication.getName()), "登录成功")));
    }
}
