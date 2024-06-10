package com.zmxstudy.rbac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zmxstudy.rbac.entity.Auth;
import com.zmxstudy.rbac.entity.Role;
import com.zmxstudy.rbac.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author star
 * @description 针对表【user(系统用户)】的数据库操作Mapper
 * @createDate 2024-04-03 15:37:14
 * @Entity com.zmxstudy.rbac.entity.User
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据用户名查询用户ID
     *
     * @param username 用户名
     * @return 用户ID
     */
    @Select("SELECT id FROM user WHERE username=#{username}")
    Long selectIdByUsername(@Param("username") String username);

    /**
     * 根据用户ID查询用户角色列表
     *
     * @param userId 用户ID
     * @return 用户角色列表
     */
    @Select("SELECT id,rolename from user_role LEFT JOIN role ON user_role.role_id=role.id WHERE user_id=#{userId}")
    List<Role> listRolesByUserId(@Param("userId") Long userId);

    /**
     * 根据用户ID查询用户角色ID列表
     *
     * @param userId 用户ID
     * @return 用户角色ID列表
     */
    @Select("SELECT id from user_role LEFT JOIN role ON user_role.role_id=role.id WHERE user_id=#{userId}")
    List<Long> listRoleIdsByUserId(@Param("userId") Long userId);

    @Select("<script>" +
            "SELECT DISTINCT auth.* FROM role_auth LEFT JOIN auth ON role_auth.auth_id=auth.id WHERE role_auth.role_id IN " +
            "<foreach collection='roleIds' item='item' open='(' separator=',' close=')'>" +
            "   #{item}" +
            "</foreach>" +
            "</script>")
    List<Auth> listAuthsByRoleIds(@Param("roleIds") List<Long> roleIds);

    @Delete("DELETE FROM user_role WHERE user_id=#{userId}")
    boolean deleteRolesByUserId(@Param("userId") Long userId);

    @Insert("<script>" +
            "INSERT INTO user_role(user_id,role_id) VALUES " +
            "<foreach collection='roleIds' item='item' separator=','>" +
            "   (#{userId}, #{item})" +
            "</foreach>" +
            "</script>")
    boolean insertRolesBatch(@Param("userId") Long userId, @Param("roleIds") List<Long> roleIds);

    @Insert("insert into " +
            "user(username, password, nickname, gender, phone, email, avatar_path, is_deleted, status, pwd_reset_time, create_by, update_by) " +
            "values(#{username}, #{password}, #{nickname}, #{gender}, #{phone}, #{email}, #{avatarPath}, #{isDeleted}, #{status}, NOW(), #{username}, #{username})")
    boolean insertUser(User user);

    @Update("<script>" +
            "update user set " +
            "<if test='password != null'> password = #{password}, </if>" +
            "<if test='avatarPath != null'> avatar_path = #{avatarPath}, </if>" +
            "<if test='nickname != null'> nickname = #{nickname}, </if>" +
            "<if test='phone != null'> phone = #{phone}, </if>" +
            "<if test='email != null'> email = #{email}, </if>" +
            "update_by = #{username}" +
            "where username = #{username}" +
            "</script>")
    boolean editUser(User user);
}




