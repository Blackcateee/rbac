package com.zmxstudy.rbac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zmxstudy.rbac.entity.Auth;
import com.zmxstudy.rbac.entity.Role;
import com.zmxstudy.rbac.entity.User;

import java.util.List;

/**
 * @author star
 * @description 针对表【user(系统用户)】的数据库操作Service
 * @createDate 2024-04-03 15:37:14
 */
public interface UserService extends IService<User> {
    /**
     * 根据用户名获取用户
     * @param username  用户名
     * @return  用户信息
     */
    User getUser(String username);

    /**
     * 根据用户名查询用户角色列表
     *
     * @param username 用户名
     * @return 角色列表
     */
    List<Role> findRoles(String username);

    /**
     * 根据用户名查询用户权限列表
     *
     * @param username 用户名
     * @return 权限列表
     */
    List<Auth> findAuths(String username);

    /**
     * 根据用户名修改用户角色
     *
     * @param username 用户名
     * @param roleIds  角色id列表
     * @return /
     */
    boolean editRoles(String username, List<Long> roleIds);
}
