package com.zmxstudy.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zmxstudy.rbac.entity.Role;
import com.zmxstudy.rbac.mapper.RoleMapper;
import com.zmxstudy.rbac.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author star
 * @description 针对表【role(角色表)】的数据库操作Service实现
 * @createDate 2024-04-03 15:37:14
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
        implements RoleService {

    @Override
    public boolean editRoles(Long roleId, List<Long> authIds) {
        if (Objects.isNull(roleId)) {
            throw new RuntimeException("角色不能为空");
        }
        if (Objects.isNull(authIds) || authIds.size() == 0) {
            throw new RuntimeException("没有需要修改的权限");
        }
        baseMapper.deleteAuthsByRoleId(roleId);
        baseMapper.insertRolesBatch(roleId, authIds);
        return true;
    }
}




