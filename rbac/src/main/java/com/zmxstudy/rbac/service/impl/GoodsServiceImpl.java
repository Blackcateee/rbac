package com.zmxstudy.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zmxstudy.rbac.entity.User;
import com.zmxstudy.rbac.entity.goods;
import com.zmxstudy.rbac.entity.tenant;
import com.zmxstudy.rbac.mapper.GoodsMapper;
import com.zmxstudy.rbac.service.GoodsServise;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

@Component
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, goods>
        implements GoodsServise {

    @Override
    public List<goods> getGoodsall() {
        List<goods> goods = baseMapper.selectAllByID();
        return goods;
    }

    @Override
    public List<goods> getGoodByuid(int uid) {
        List<goods> goodsList = baseMapper.getGoodByuid(uid);
        return goodsList;
    }

    @Override
    public goods getGoodBygid(int gid) {
        goods goods = baseMapper.getGoodBygid(gid);
        return goods;
    }

    @Override
    public boolean deleteGoodsBygId(int gid) {
        boolean b = baseMapper.deleteGoodsBygId(gid);
        return b;
    }

    @Override
    public boolean insertGoods(goods goods,int uid) {
        goods.setUid(uid);
        boolean register=baseMapper.insertGoods(goods);
        return register;
    }

    @Override
    public boolean checkin(int gid) {
        goods good = baseMapper.getGoodBygid(gid);
        good.setStatus(1);
        boolean b = baseMapper.updateGoods(good);
        return b;
    }


    @Override
    public boolean checkout(int gid) {
        goods good = baseMapper.getGoodBygid(gid);
        good.setStatus(0);
        boolean b = baseMapper.updateGoods(good);
        return b;
    }


    @Override
    public boolean updateGoods(goods goods) {
        boolean b = baseMapper.updateGoods(goods);
        return b;
    }

    public String avatarUpload(String goodsname, MultipartFile file) throws Exception{
        InputStream inputStream = file.getInputStream();
        File avatar = new File("D:\\workspace\\rbac\\rbac\\src\\main\\resources\\static\\upload\\avatar\\" + goodsname + ".jpg");
        FileOutputStream fileOutputStream = new FileOutputStream(avatar);
        fileOutputStream.write(inputStream.readAllBytes());
        fileOutputStream.close();
        goods g= new goods();
        g.setGname(goodsname);
        g.setGphoto("rbac/rbac/src/main/resources/static/upload/avatar/" + goodsname + ".jpg");
        baseMapper.updateGoods(g);
        return "rbac/rbac/src/main/resources/static/upload/avatar/" + goodsname + ".jpg";
    }
}
