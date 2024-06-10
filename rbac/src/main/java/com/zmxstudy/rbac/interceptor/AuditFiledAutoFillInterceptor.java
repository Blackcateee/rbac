package com.zmxstudy.rbac.interceptor;

import com.baomidou.mybatisplus.core.plugins.InterceptorIgnoreHelper;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.Connection;

/**
 * MybatisPlus自定义自定填充create_by和update_by的拦截器
 *
 * @author star
 */
public class AuditFiledAutoFillInterceptor implements InnerInterceptor {
    private static final String CREATE_BY_KEY_REGEX = "\\)\s*(?i)values";
    private static final String CREATE_BY_VALUE_REGEX = "\\)$";
    private static final String UPDATE_BY_REGEX = "(?i)set\s*";
//    该拦截器不能在没有登陆的情况下使用，需要注掉
//    /**
//     * 在预编译前拦截处理
//     */
//    @Override
//    public void beforePrepare(StatementHandler sh, Connection connection, Integer transactionTimeout) {
//        PluginUtils.MPStatementHandler mpSh = PluginUtils.mpStatementHandler(sh);
//        MappedStatement ms = mpSh.mappedStatement();
//        SqlCommandType sct = ms.getSqlCommandType();
//        if (sct == SqlCommandType.INSERT || sct == SqlCommandType.UPDATE) {
//            if (InterceptorIgnoreHelper.willIgnoreDynamicTableName(ms.getId())) {
//                return;
//            }
//            PluginUtils.MPBoundSql mpBs = mpSh.mPBoundSql();
//            String sql = mpBs.sql();
//            mpBs.sql(processFill(sct, sql));
//        }
//    }
//
//    /**
//     * 处理数据自动填充
//     */
//    protected String processFill(SqlCommandType sct, String sql) {
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        // 新增数据的时候，自定插入create_by的值为当前登录用户名
//        if (sct == SqlCommandType.INSERT) {
//            sql = sql.replaceAll(CREATE_BY_KEY_REGEX, ",create_by" + ")VALUES");
//            sql = sql.replaceAll(CREATE_BY_VALUE_REGEX, ",'" + username + "')");
//            System.out.println(sql);
//        }
//        // 修改数据的时候，自定插入create_by的值为当前登录用户名
//        else if (sct == SqlCommandType.UPDATE) {
//            sql = sql.replaceAll(UPDATE_BY_REGEX, "SET update_by='" + username + "',");
//            System.out.println(sql);
//        }
//        return sql;
//    }
}
