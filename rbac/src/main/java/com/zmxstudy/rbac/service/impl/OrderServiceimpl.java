package com.zmxstudy.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zmxstudy.rbac.entity.goods;
import com.zmxstudy.rbac.entity.order;
import com.zmxstudy.rbac.mapper.GoodsMapper;
import com.zmxstudy.rbac.mapper.OrderMapper;
import com.zmxstudy.rbac.service.GoodsServise;
import com.zmxstudy.rbac.service.orderService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceimpl extends ServiceImpl<OrderMapper, order>
        implements orderService {
    @Override
    public boolean insertOrder(int uid, int gid) {
        order o=new order();
        o.setUid(uid);
        o.setGid(gid);
        System.out.println(o);
        boolean b = baseMapper.insertOrder(o);
        return b;
    }

    @Override
    public List<goods> getorder(int uid) {
        List<goods> b = baseMapper.getorder(uid);
        return  b;
    }
}
