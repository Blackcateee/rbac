package com.zmxstudy.rbac.handler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author star
 */
@Component
@AllArgsConstructor
public class SecurityAuthenticationAccessHandler implements AuthorizationManager<HttpServletRequest> {
    private AntPathMatcher antPathMatcher;


    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, HttpServletRequest request) {
        return new AuthorizationDecision(authentication
                .get()
                .getAuthorities()
                .stream()
                .anyMatch((Predicate<GrantedAuthority>) grantedAuthority -> {
                            return antPathMatcher.match(grantedAuthority.getAuthority(), request.getRequestURI());
                        }
                ));
    }
}
