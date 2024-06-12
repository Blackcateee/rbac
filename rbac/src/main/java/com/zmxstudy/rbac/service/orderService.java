package com.zmxstudy.rbac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zmxstudy.rbac.entity.goods;
import com.zmxstudy.rbac.entity.order;
import com.zmxstudy.rbac.entity.tenant;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

public interface orderService extends IService<order> {
    /**
     * 商品加入订单
     *
     * @param uid 用户id
     * @param gid 商品id
     * @return
     */
    boolean insertOrder(@RequestHeader("uid") int uid, @RequestHeader("gid") int gid);
    /**
     * 查询订单
     *
     * @param uid 用户id
     * @return
     */
    List<goods> getorder(@RequestHeader("uid") int uid);
}
