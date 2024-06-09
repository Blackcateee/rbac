package com.zmxstudy.rbac.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 昵称
     */
    @TableField(value = "nickname")
    private String nickname;

    /**
     * 性别：男；女
     */
    @TableField(value = "gender")
    private String gender;

    /**
     * 手机号码
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 头像地址
     */
    @TableField(value = "avatar_file")
    private String avatarFile;

    /**
     * 头像真实路径
     */
    @TableField(value = "avatar_path")
    private String avatarPath;

    /**
     * 是否逻辑删除：1-删除；0-未删除
     */
    @TableField(value = "is_deleted")
    private Boolean isDeleted;

    /**
     * 状态：1-启用；0-禁用
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 修改密码的时间
     */
    @TableField(value = "pwd_reset_time")
    private Date pwdResetTime;

    /**
     * 创建者
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 更新者
     */
    @TableField(value = "update_by")
    private String updateBy;

    /**
     * 创建日期
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}