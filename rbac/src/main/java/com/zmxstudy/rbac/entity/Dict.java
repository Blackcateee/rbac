package com.zmxstudy.rbac.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据字典
 * @TableName dict
 */
@TableName(value ="dict")
@Data
public class Dict implements Serializable {
    /**
     * 字典ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 后端获取字典的英文名称
     */
    @TableField(value = "dictname")
    private String dictname;

    /**
     * 前端展示字典的中文标签
     */
    @TableField(value = "label")
    private String label;

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