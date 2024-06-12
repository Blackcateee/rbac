package com.zmxstudy.rbac.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName(value ="order")
@Data
public class order {
    @TableField(value = "gid")
    private int gid;
    @TableField(value = "uid")
    private int uid;
}
