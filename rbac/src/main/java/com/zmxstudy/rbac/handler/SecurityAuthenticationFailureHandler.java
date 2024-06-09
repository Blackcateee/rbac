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
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 登录失败处理器，一般是用户密码错误
 *
 * @author star
 */
@Component
@AllArgsConstructor
public class SecurityAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        ResponseUtil.writeJson(response, objectMapper.writeValueAsString(Result.error(ResultCode.CLIENT_A0212)));
    }
}
