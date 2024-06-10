package com.zmxstudy.rbac.controller;

import com.zmxstudy.rbac.base.BaseController;
import com.zmxstudy.rbac.entity.Auth;
import com.zmxstudy.rbac.entity.Role;
import com.zmxstudy.rbac.entity.User;
import com.zmxstudy.rbac.service.UserService;
import com.zmxstudy.rbac.vo.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author star
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController<UserService, User> {
    /**
     * 根据用户名获取用户个人信息
     *
     * @param username 用户名
     * @return 用户个人信息
     */
    @GetMapping("/info")
    public User get(@RequestHeader("username") String username) {
        return baseService.getUser(username);
    }

    /**
     * 根据用户名获取用户角色列表
     *
     * @param username 用户名
     * @return 用户角色列表
     */
    @GetMapping("info/roles")
    public List<Role> findRoles(@RequestHeader("username") String username) {
        return baseService.findRoles(username);
    }

    /**
     * 根据用户名获取用户权限列表
     *
     * @param username 用户名
     * @return 用户权限列表
     */
    @GetMapping("info/auths")
    public List<Auth> findAuths(@RequestHeader("username") String username) {
        return baseService.findAuths(username);
    }

    /**
     * 根据用户名修改用户权限列表
     *
     * @param username 用户名
     * @param roleIds  新的用户权限列表
     * @return /
     */
    @PutMapping("edit/roles")
    public boolean editRoles(@RequestHeader("username") String username, @RequestBody List<Long> roleIds) {
        return baseService.editRoles(username, roleIds);
    }

}
