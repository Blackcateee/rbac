package com.zmxstudy.rbac.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统菜单
 * @TableName auth
 */
@TableName(value ="auth")
@Data
public class Auth implements Serializable {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 上级权限ID
     */
    @TableField(value = "pid")
    private Long pid;

    /**
     * 下级权限数目
     */
    @TableField(value = "count_sub")
    private Integer countSub;

    /**
     * 前端菜单显示的排序
     */
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 前端菜单类型:0-顶部菜单；1-一级菜单；2-子菜单；3-页面资源；4-外联
     */
    @TableField(value = "type")
    private String type;

    /**
     * 前端菜单（路由）名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 前端菜单（路由）地址：user/page
     */
    @TableField(value = "path")
    private String path;

    /**
     * 前端菜单（路由）父级映射地址：user/page
     */
    @TableField(value = "redirect")
    private String redirect;

    /**
     * 前端菜单（路由）组件：components/icons/index
     */
    @TableField(value = "component")
    private String component;

    /**
     * 前端菜单（路由）图标
     */
    @TableField(value = "meta_icon")
    private String metaIcon;

    /**
     * 前端菜单（路由）标题
     */
    @TableField(value = "meta_title")
    private String metaTitle;

    /**
     * 前端菜单权限：user:page
     */
    @TableField(value = "permission")
    private String permission;

    /**
     * 前端菜单是否是外链链接
     */
    @TableField(value = "href")
    private String href;

    /**
     * 前端菜单是否隐藏：1-删除；0-未删除
     */
    @TableField(value = "is_hidden")
    private Boolean isHidden;

    /**
     * 后端controller映射的地址
     */
    @TableField(value = "uri")
    private String uri;

    /**
     * 后端微服务模块名称
     */
    @TableField(value = "module")
    private String module;

    /**
     * 是否逻辑删除：1-删除；0-未删除
     */
    @TableField(value = "is_deleted")
    private Boolean isDeleted;

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