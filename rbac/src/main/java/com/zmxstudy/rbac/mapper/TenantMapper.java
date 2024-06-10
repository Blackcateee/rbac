package com.zmxstudy.rbac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zmxstudy.rbac.entity.Auth;
import com.zmxstudy.rbac.entity.Role;
import com.zmxstudy.rbac.entity.tenant;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TenantMapper extends BaseMapper<tenant> {
    /**
     * 根据商户ID查询商户表所有字段
     *
     * @return 商户信息集合
     */
    @Select("SELECT * FROM tenant")
    List<tenant> selectAllByID();
    /**
     * 根据商户ID查询商户表所有字段
     *
     * @param id 商户ID
     * @return 商户信息集合
     */
    @Select("SELECT * FROM tenant WHERE id=#{id}")
    tenant selectTenantByID(@Param("id") int id);
    /**
     * 根据商户ID删除商户
     *
     * @param id 商户ID
     * @return
     */
    @Delete("DELETE FROM tenant WHERE id=#{id}")
    boolean deleteTenantById(@Param("id") int id);
    /**
     * 新增商户商户
     *
     * @param tenant 商户
     * @return
     */
    @Insert("<script>" +
            "INSERT INTO tenant(name,account,password,phone,email,avatar_name,avatar_path) VALUES (#{name},#{account},#{password},#{phone},#{email},#{avatarName},#{avatarPath})" +
            "</script>")
    boolean insertTenant(@Param("tenant") tenant tenant);
    /**
     * 编辑商户
     *
     * @param tenant 商户
     * @return
     */
    @Update("<script>" +
            "UPDATE SET tenant name=#{name},account=#{account},password=#{password},phone={phone},email={email},avatar_name=#{avatar_name},avatar_path=#{avatar_path})" +
            "</script>")
    boolean updateTenant(@Param("tenant") tenant tenant);
}
