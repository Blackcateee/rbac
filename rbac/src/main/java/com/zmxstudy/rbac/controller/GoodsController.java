package com.zmxstudy.rbac.controller;

import com.zmxstudy.rbac.base.BaseController;
import com.zmxstudy.rbac.entity.goods;
import com.zmxstudy.rbac.entity.tenant;
import com.zmxstudy.rbac.service.GoodsServise;
import com.zmxstudy.rbac.service.TenantServise;
import com.zmxstudy.rbac.vo.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController extends BaseController<GoodsServise, goods> {
    /**
     * 查询商品表所有字段
     *
     * @return 商品信息集合
     */
    @GetMapping("/all")
    public List<goods> getGoodsall() {
        return baseService.getGoodsall();
    }
    /**
     * 根据查询当前登录商家的ID所对应的所有商品
     *
     * @return 商户信息集合
     */
    @GetMapping("/byuid")
    public List<goods> getGoodByuid(@RequestHeader("uid") int uid) {
        return baseService.getGoodByuid(uid);
    }
    /**
     * 根据商品的gid查询某种商品信息
     *
     * @return 商户信息集合
     */
    @GetMapping("/bygid")
    public goods getGoodBygid(@RequestHeader("gid") int gid) {
        return baseService.getGoodBygid(gid);
    }
    /**
     * 根据商品gid删除商品
     *
     * @param gid 商户ID
     * @return
     */
    @DeleteMapping("/delbyid")
    public boolean deleteGoodsBygId(@RequestHeader("gid") int gid) {
        return baseService.deleteGoodsBygId(gid);
    }
    /**
     * 商品上架
     *
     * @param goods 商品
     * @return
     */
    @PostMapping("/insert")
    public boolean insertGoods(@RequestBody goods goods,@RequestHeader("uid") int uid) {
        return baseService.insertGoods(goods,uid);
    }
    /**
     * 下架商品
     *
     * @param goods 商品
     * @return
     */
    @PostMapping("/update")
    public boolean updateTenant(@RequestBody goods goods) {
        return baseService.updateGoods(goods);
    }

    @PostMapping("/avatarUpload")
    public Result<String> avatarUpload(@RequestHeader("goodsname") String goodsname, MultipartFile file)  throws Exception{
        return Result.ok(baseService.avatarUpload(goodsname, file));
    }
}