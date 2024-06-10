package com.zmxstudy.rbac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zmxstudy.rbac.entity.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author star
 * @description 针对表【role(角色表)】的数据库操作Mapper
 * @createDate 2024-04-03 15:37:14
 * @Entity com.zmxstudy.rbac.entity.Role
 */
public interface RoleMapper extends BaseMapper<Role> {
    @Delete("DELETE FROM role_auth WHERE role_id=#{roleId}")
    boolean deleteAuthsByRoleId(@Param("roleId") Long roleId);

    @Insert("<script>" +
            "INSERT INTO role_auth(role_id,auth_id) VALUES " +
            "<foreach collection='authIds' item='item' separator=','>" +
            "   (#{roleId}, #{item})" +
            "</foreach>" +
            "</script>")
    boolean insertRolesBatch(@Param("roleId") Long roleId, @Param("authIds") List<Long> authIds);

    @Select("select * from role")
    List<Role> selectRoles();
}




