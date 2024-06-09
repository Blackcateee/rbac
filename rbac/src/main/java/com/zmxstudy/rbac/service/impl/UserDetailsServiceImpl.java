package com.zmxstudy.rbac.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.zmxstudy.rbac.entity.Auth;
import com.zmxstudy.rbac.entity.Role;
import com.zmxstudy.rbac.entity.User;
import com.zmxstudy.rbac.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author star
 */
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserService userService;

    /**
     * 根据用户名获取用户信息、角色列表、权限列表
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        // 1、加载数据库中用户信息
        User user = userService.getUser(username);
        if (ObjectUtil.isEmpty(user)) {
            throw new UsernameNotFoundException("用户不存在");
        }
        if (ObjectUtil.isEmpty(user.getPassword())) {
            throw new BadCredentialsException("密码无效");
        }

        LinkedList<GrantedAuthority> authorities = new LinkedList<>();

        // 2、加载数据库中角色信息
        List<Role> roles = userService.findRoles(username);
        for (Role role : roles) {
            if (Objects.nonNull(role)) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRolename()));
            }
        }

        // 3、加载数据库中权限信息
        for (Auth auth : userService.findAuths(username)) {
            if (Objects.nonNull(auth)) {
                authorities.add(new SimpleGrantedAuthority(auth.getUri()));
            }
        }

        // 使用用户信息、角色列表、权限列表构建出Userdetails对象
        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .authorities(authorities)
                .password(user.getPassword())
                .build();
    }
}
