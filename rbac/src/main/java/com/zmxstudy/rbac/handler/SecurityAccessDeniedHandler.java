package com.zmxstudy.rbac.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zmxstudy.rbac.util.ResponseUtil;
import com.zmxstudy.rbac.vo.Result;
import com.zmxstudy.rbac.vo.ResultCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 登录后无权访问处理器
 *
 * @author star
 */
@Component
@AllArgsConstructor
public class SecurityAccessDeniedHandler implements AccessDeniedHandler {
    private ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseUtil.writeJson(response, objectMapper.writeValueAsString(Result.error(ResultCode.CLIENT_A0312)));
    }
}
