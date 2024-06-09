package com.zmxstudy.rbac.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

/**
 * 退出处理器
 *
 * @author star
 */
@Component
@AllArgsConstructor
public class SecurityLogoutHandler implements LogoutHandler {

    @SneakyThrows
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
    }
}
