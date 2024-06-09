package com.zmxstudy.rbac.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zmxstudy.rbac.util.ResponseUtil;
import com.zmxstudy.rbac.vo.Result;
import com.zmxstudy.rbac.vo.ResultCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 匿名用户访问无权限处理器
 *
 * @author star
 */
@Component
@AllArgsConstructor
public class SecurityAuthenticationEntryPointHandler implements AuthenticationEntryPoint {
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseUtil.writeJson(response, objectMapper.writeValueAsString(Result.error(ResultCode.CLIENT_A0300)));
    }
}
