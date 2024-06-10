package com.zmxstudy.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zmxstudy.rbac.entity.User;
import com.zmxstudy.rbac.entity.tenant;
import com.zmxstudy.rbac.mapper.TenantMapper;
import com.zmxstudy.rbac.mapper.UserMapper;
import com.zmxstudy.rbac.service.TenantServise;
import com.zmxstudy.rbac.service.UserService;
import jakarta.annotation.Resource;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TenantServiceImpl extends ServiceImpl<TenantMapper, tenant>
        implements TenantServise {
    @Resource
    public PasswordEncoder passwordEncoder;
    @Resource
    public UserServiceImpl userServiceimpl;
    @Resource
    public UserMapper userMapper;
    @Override
    public List<tenant> gettenants() {
        List<tenant> tenants = baseMapper.selectAllByID();
        return tenants;
    }

    @Override
    public tenant gettenant(int id) {
        tenant tenant = baseMapper.selectTenantByID(id);
        return tenant;
    }

    @Override
    public boolean deleteTenantById(int id) {
        boolean b = baseMapper.deleteTenantById(id);
        return b;
    }

    @Override
    public boolean insertTenant(tenant tenant) {
        tenant.setIsDeleted(true);
        tenant.setStatus(1);
        tenant.setCreateBy("admin");
        tenant.setPassword(tenant.getPassword());
        tenant.setCreateTime(Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        //把商家注册到用户表
        User u=new User();
        u.setIsDeleted(tenant.getIsDeleted());
        u.setCreateTime(tenant.getCreateTime());
        u.setCreateBy(tenant.getCreateBy());
        u.setAvatarPath(tenant.getAvatarPath());
        u.setStatus(1);
        u.setEmail(tenant.getEmail());
        u.setPassword(tenant.getPassword());
        u.setPhone(tenant.getPhone());
        List<Long> longList = new ArrayList<>();
        // 添加管理员为商户的编码到列表中
        longList.add(3L);
        u.setRoleId(longList);
        boolean register = userServiceimpl.register(u);
        boolean register2 = baseMapper.insertTenant(tenant);
        if (register=true&&register2==true){
            return true;
        }else
        return false;
    }

    @Override
    public boolean updateTenant(tenant tenant) {
        tenant.setUpdateBy("admin");
        tenant.setUpdateTime(Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        boolean b = baseMapper.updateTenant(tenant);
        return b;
    }
}
