package com.zmxstudy.rbac.controller;

import com.zmxstudy.rbac.base.BaseController;
import com.zmxstudy.rbac.entity.goods;
import com.zmxstudy.rbac.entity.order;
import com.zmxstudy.rbac.service.orderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author star
 */
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController<orderService,order> {
    /**
     * 商品加入订单
     *
     * @param uid 用户id
     * @param gid 商品id
     * @return
     */
    @PostMapping("/add")
    public boolean insertOrder(@RequestHeader("uid") int uid,@RequestHeader("gid") int gid) {
        System.out.println(uid);
        System.out.println(gid);
        return baseService.insertOrder(uid,gid);
    }

    /**
     * 查询订单
     *
     * @param uid 用户id
     * @return
     */
    @PostMapping("/getorder")
    public List<goods> getorder(@RequestHeader("uid") int uid) {
        return baseService.getorder(uid);
    }

}
