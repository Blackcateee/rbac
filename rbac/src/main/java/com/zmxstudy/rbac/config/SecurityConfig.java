package com.zmxstudy.rbac.config;

import com.zmxstudy.rbac.filter.SecurityJwtAuthenticationFilter;
import com.zmxstudy.rbac.handler.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.AntPathMatcher;

/**
 * Security 配置
 *
 * @author star
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    public static final String LOGIN_URI = "/login";
    public static final String[] WHITE_LIST = new String[]{
            "/static", "/error",
            "/actuator/health", "/actuator/info", "/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**", "/swagger-resources/**"
    };

    /**
     * 路径匹配方式
     */
    @Bean
    public AntPathMatcher antPathMatcher() {
        return new AntPathMatcher();
    }

    /**
     * 配置Security加密方式
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置Security授权规则
     */
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            SecurityAuthenticationSuccessHandler securityAuthenticationSuccessHandler,
            SecurityAuthenticationFailureHandler securityAuthenticationFailureHandler,
            SecurityAuthenticationAccessHandler securityAuthenticationAccessHandler,
            SecurityAuthenticationEntryPointHandler securityAuthenticationEntryPointHandler,
            SecurityLogoutHandler securityLogoutHandler,
            SecurityLogoutSuccessHandler securityLogoutSuccessHandler,
            SecurityAccessDeniedHandler securityAccessDeniedHandler,
            SecurityJwtAuthenticationFilter securityJwtAuthenticationFilter
    ) throws Exception {
        // 关闭表单的防伪造请求（在表单中默认携带一个随机token字符串），因为前后端分离没办法保证token
        http.csrf().disable();
        // 开启和配置表单登录方式
        http.formLogin(customizer -> customizer
                // 配置表单登录成功后的处理方法
                .successHandler(securityAuthenticationSuccessHandler)
                // 配置表单登录失败后的处理方法
                .failureHandler(securityAuthenticationFailureHandler)
                .permitAll()
        );
        // 退出策略
        http.logout(customizer -> customizer
                //.addLogoutHandler(securityLogoutHandler)
                .logoutSuccessHandler(securityLogoutSuccessHandler)
                .permitAll()
        );
        // 异常处理
        http.exceptionHandling(customizer -> customizer
                .authenticationEntryPoint(securityAuthenticationEntryPointHandler)
                .accessDeniedHandler(securityAccessDeniedHandler)
        );
        // 对Security设置授权规则
        http.authorizeHttpRequests((authorize) -> authorize
                        // 静态资源，无条件允许
                        .requestMatchers(WHITE_LIST).permitAll()
                        .anyRequest().authenticated()
                // 其他所有资源，通过自定义规则授权
                //.anyRequest().access((authentication, request) -> securityAuthenticationAccessHandler.check(authentication, request.getRequest()))
        );
        // 添加自定义认证过滤器
        http.addFilterBefore(
                securityJwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class
        );
        // Session管理策略
        http.sessionManagement(customizer -> customizer
                .sessionCreationPolicy(SessionCreationPolicy.NEVER)
        );
        // 构造出SecurityFilterChain对象并返回
        return http.build();
    }
}
