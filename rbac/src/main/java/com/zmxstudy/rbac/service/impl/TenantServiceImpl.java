package com.zmxstudy.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zmxstudy.rbac.entity.User;
import com.zmxstudy.rbac.entity.tenant;
import com.zmxstudy.rbac.mapper.TenantMapper;
import com.zmxstudy.rbac.mapper.UserMapper;
import com.zmxstudy.rbac.service.TenantServise;
import com.zmxstudy.rbac.service.UserService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class TenantServiceImpl extends ServiceImpl<TenantMapper, tenant>
        implements TenantServise {

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
        tenant.setCreateTime(Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        boolean b = baseMapper.insertTenant(tenant);
        return b;
    }

    @Override
    public boolean updateTenant(tenant tenant) {
        tenant.setUpdateBy("admin");
        tenant.setUpdateTime(Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        boolean b = baseMapper.updateTenant(tenant);
        return b;
    }
}
