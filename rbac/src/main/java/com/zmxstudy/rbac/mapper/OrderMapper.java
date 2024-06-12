package com.zmxstudy.rbac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zmxstudy.rbac.entity.goods;
import com.zmxstudy.rbac.entity.order;
import com.zmxstudy.rbac.entity.tenant;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderMapper extends BaseMapper<order> {
    /**
     * 新增商户商户
     *
     * @param order 订单
     * @return
     */
    @Insert("<script>" +
            "INSERT INTO order(gid,uid) VALUES (#{gid},#{uid})" +
            "</script>")
    boolean insertOrder(@Param("order") order order);


    /**
     * 查询订单
     *
     * @param uid 用户id
     * @return
     */
    @Select("SELECT * from `order` LEFT JOIN good ON `order`.gid=good.gid WHERE `order`.uid=#{uid}")
    List<goods> getorder(@Param("uid") int uid);
}
