package com.zmxstudy.rbac.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName(value ="goods")
@Data
public class goods {
    @TableId(value = "gid", type = IdType.AUTO)
    private int gid;
    @TableField(value = "gname")
    private String gname;
    @TableField(value = "gphoto")
    private String gphoto;
    @TableField(value = "gnum")
    private int gnum;
    @TableField(value = "gprice")
    private int gprice;
    @TableField(value = "uid")
    private int uid;
    @TableField(value = "status")
    private int status;
}
