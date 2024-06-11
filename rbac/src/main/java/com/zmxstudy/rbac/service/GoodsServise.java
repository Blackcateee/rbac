package com.zmxstudy.rbac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zmxstudy.rbac.entity.goods;
import com.zmxstudy.rbac.entity.tenant;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GoodsServise extends IService<goods> {
    /**
     * 查询商品表所有字段
     *
     * @return 商品信息集合
     */
    public List<goods> getGoodsall();
    /**
     * 根据查询当前登录商家的ID所对应的所有商品
     *
     * @return 商户信息集合
     */
    public List<goods> getGoodByuid(@RequestHeader("uid") int uid) ;
    /**
     * 根据商品的gid查询某种商品信息
     *
     * @return 商户信息集合
     */
    public goods getGoodBygid(@RequestHeader("gid") int gid);
    /**
     * 根据商品gid删除商品
     *
     * @param gid 商户ID
     * @return
     */
    public boolean deleteGoodsBygId(@RequestHeader("gid") int gid) ;
    /**
     * 商品上架
     *
     * @param goods 商品
     * @return
     */
    public boolean insertGoods(@RequestBody goods goods,@RequestHeader("uid") int uid) ;
    /**
     * 下架商品
     *
     * @param goods 商品
     * @return
     */
    public boolean updateGoods(@RequestBody goods goods);

    String avatarUpload(String goodsname, MultipartFile file)  throws Exception;

}
