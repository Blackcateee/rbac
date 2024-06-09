package com.zmxstudy.rbac.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据字典详情
 * @TableName dict_detail
 */
@TableName(value ="dict_detail")
@Data
public class DictDetail implements Serializable {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 字典ID
     */
    @TableField(value = "dict_id")
    private Long dictId;

    /**
     * 字典值
     */
    @TableField(value = "key")
    private String key;

    /**
     * 字典值
     */
    @TableField(value = "value")
    private String value;

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