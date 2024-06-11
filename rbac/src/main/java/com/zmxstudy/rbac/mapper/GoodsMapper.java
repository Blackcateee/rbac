package com.zmxstudy.rbac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zmxstudy.rbac.entity.goods;
import com.zmxstudy.rbac.entity.tenant;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface GoodsMapper extends BaseMapper<goods> {
    /**
     * 根据商户ID查询商户表所有字段
     *
     * @return 商户信息集合
     */
    @Select("SELECT * FROM good")
    List<goods> selectAllByID();
    /**
     * 根据查询当前登录商家的ID所对应的所有商品
     *
     * @return 商户信息集合
     */
    @Select("SELECT * FROM good WHERE uid=#{uid}")
    List<goods> getGoodByuid(int uid);
    /**
     * 根据商品的gid查询某种商品信息
     *
     * @return 商户信息集合
     */
    @Select("SELECT * FROM good WHERE uid=#{gid}")
    goods getGoodBygid(int gid);
    /**
     * 根据商品gid删除商品
     *
     * @param gid 商品ID
     * @return
     */
    @Delete("DELETE FROM good WHERE gid=#{gid}")
    boolean deleteGoodsBygId(int gid);
    /**
     * 新增商品
     *
     * @param goods 商户
     * @return
     */
    @Insert("<script>" +
            "INSERT INTO good(gname,gphoto,gnum,gprice,uid) VALUES (#{gname},#{gphoto},#{gnum},#{gprice},#{uid})" +
            "</script>")
    boolean insertGoods(goods goods);

    /**
     * 编辑商品
     *
     * @param goods 商户
     * @return
     */
    @Update("<script>" +
            "UPDATE SET good gname=#{gname},gphoto=#{gphoto},gnum=#{gnum},gprice={gprice},uid={uid}" +
            "</script>")
    boolean updateGoods(goods goods);
}
