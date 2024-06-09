package com.zmxstudy.rbac.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zmxstudy.rbac.constant.SecurityConstant;
import com.zmxstudy.rbac.util.JwtUtil;
import com.zmxstudy.rbac.util.RedisUtil;
import com.zmxstudy.rbac.util.ResponseUtil;
import com.zmxstudy.rbac.vo.Result;
import com.zmxstudy.rbac.vo.ResultCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 退出成功处理器
 *
 * @author star
 */
@Component
@AllArgsConstructor
public class SecurityLogoutSuccessHandler implements LogoutSuccessHandler {
    private JwtUtil jwtUtil;
    private RedisUtil redisUtil;
    private ObjectMapper objectMapper;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 清除Session（前后端不分离）
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }
        try {
            String jwt = request.getHeader(SecurityConstant.TOKEN_HEADER);
            String name = jwtUtil.parse(jwt).getSubject();
            // 清除Redis（前后端分离）
            redisUtil.hashDelete(SecurityConstant.TOKEN_KEY, name);
            ResponseUtil.writeJson(response, objectMapper.writeValueAsString(Result.ok("退出成功")));
        } catch (Exception e) {
            ResponseUtil.writeJson(response, objectMapper.writeValueAsString(Result.error(ResultCode.CLIENT_A0304)));
        }
    }
}
