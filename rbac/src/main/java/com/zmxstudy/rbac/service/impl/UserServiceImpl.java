package com.zmxstudy.rbac.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zmxstudy.rbac.entity.Auth;
import com.zmxstudy.rbac.entity.Role;
import com.zmxstudy.rbac.entity.User;
import com.zmxstudy.rbac.mapper.UserMapper;
import com.zmxstudy.rbac.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author star
 * @description 针对表【user(系统用户)】的数据库操作Service实现
 * @createDate 2024-04-03 15:37:14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Override
    public User getUser(String username) {
        return getOne(new QueryWrapper<User>().eq("username", username));
    }

    @Override
    public List<Role> findRoles(String username) {
        Long userId = baseMapper.selectIdByUsername(username);
        if (ObjectUtil.isEmpty(userId)) {
            return Collections.emptyList();
        }
        return baseMapper.listRolesByUserId(userId);
    }

    @Override
    public List<Auth> findAuths(String username) {
        Long userId = baseMapper.selectIdByUsername(username);
        List<Long> roleIds = baseMapper.listRoleIdsByUserId(userId);
        if (ObjectUtil.isEmpty(roleIds)) {
            return Collections.emptyList();
        }
        return baseMapper.listAuthsByRoleIds(roleIds);
    }

    @Override
    public boolean editRoles(String username, List<Long> roleIds) {
        if (ObjectUtil.isEmpty(roleIds)) {
            return false;
        }
        Long userId = baseMapper.selectIdByUsername(username);
        if (ObjectUtil.isEmpty(userId)) {
            return false;
        }
        baseMapper.deleteRolesByUserId(userId);
        baseMapper.insertRolesBatch(userId, roleIds);
        return true;
    }

}




