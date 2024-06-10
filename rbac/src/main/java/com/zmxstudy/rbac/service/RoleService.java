package com.zmxstudy.rbac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zmxstudy.rbac.entity.Role;
import com.zmxstudy.rbac.entity.User;

import java.util.List;

/**
 * @author star
 * @description 针对表【role(角色表)】的数据库操作Service
 * @createDate 2024-04-03 15:37:14
 */
public interface RoleService extends IService<Role> {
    /**
     * 根据角色ID修改角色的权限
     *
     * @param roleId  角色ID
     * @param authIds 角色的权限IDs
     * @return /
     */
    boolean editRoles(Long roleId, List<Long> authIds);
}
