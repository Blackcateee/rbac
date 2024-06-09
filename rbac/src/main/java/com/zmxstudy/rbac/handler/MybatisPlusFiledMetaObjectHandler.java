package com.zmxstudy.rbac.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * MybatisPlus自定填充createTime和updateTime处理器
 *
 * @author star
 */
@Slf4j
@Component
public class MybatisPlusFiledMetaObjectHandler implements MetaObjectHandler {

    /**
     * 添加数据时自定填充字段值
     *
     * @param metaObject /
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 支持父类的字段自动填充
        // this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        // 或者
        // this.strictInsertFill(metaObject, "createTime", LocalDateTime::now, LocalDateTime.class);
        // 不支持父类的字段自动填充
        this.fillStrategy(metaObject, "createTime", LocalDateTime.now());
    }

    /**
     * 修改数据时自定填充字段值
     *
     * @param metaObject /
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        // 支持父类的字段自动填充
        // this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        // 或者
        // this.strictUpdateFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
        // 不支持父类的字段自动填充
        this.fillStrategy(metaObject, "updateTime", LocalDateTime.now());
    }
}
