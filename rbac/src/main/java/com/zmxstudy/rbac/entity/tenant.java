package com.zmxstudy.rbac.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
@TableName(value ="user")
@Data
public class tenant {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    @TableField(value = "name")
    private String name;
    @TableField(value = "account")
    private String account;
    @TableField(value = "password")
    private String password;
    @TableField(value = "phone")
    private String phone;
    @TableField(value = "email")
    private String email;
    @TableField(value = "avatarName")
    private String avatarName;
    @TableField(value = "avatarPath")
    private String avatarPath;
    @TableField(value = "isDeleted")
    private Boolean isDeleted;
    @TableField(value = "status")
    private int status;
    @TableField(value = "createBy")
    private String createBy;
    @TableField(value = "updateBy")
    private String updateBy;
    @TableField(value = "createTime")
    private Date createTime;
    @TableField(value = "updateTime")
    private Date updateTime;
}
