package com.zmxstudy.rbac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zmxstudy.rbac.entity.User;
import com.zmxstudy.rbac.entity.tenant;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface TenantServise extends IService<tenant> {
    /**
     * 根据商户ID查询商户表所有字段
     *
     * @return 商户信息集合
     */
    List<tenant> gettenants();
    /**
     * 根据商户ID查询商户表所有字段
     *
     * @param id 商户ID
     * @return 商户信息集合
     */
    tenant gettenant(@Param("id") int id);
    /**
     * 根据商户ID删除商户
     *
     * @param id 商户ID
     * @return
     */
    boolean deleteTenantById(@Param("userId") int id);
    /**
     * 新增商户商户
     *
     * @param tenant 商户
     * @return
     */
    boolean insertTenant(@Param("tenant") tenant tenant);
    /**
     * 编辑商户
     *
     * @param tenant 商户
     * @return
     */
    boolean updateTenant(@Param("tenant") tenant tenant);
}
