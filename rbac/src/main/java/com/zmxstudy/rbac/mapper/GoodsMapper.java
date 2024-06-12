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
    @Select("SELECT * FROM good WHERE gid=#{gid}")
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
     * @param good 商户
     * @return
     */
    @Insert("insert into " +
            "good(gname, gphoto, gnum, gprice,status,uid)" +
            "values(#{gname}, #{gphoto}, #{gnum}, #{gprice},#{status},#{uid})")
    boolean insertGoods(goods good);

    /**
     * 编辑商品
     *a
     * @param goods 商户
     * @return
     */
    @Update({
            "<script>",
            "update good",
            "<set>",
            "<if test='gname != null'>gname = #{gname},</if>",
            "<if test='gphoto != null'>gphoto = #{gphoto},</if>",
            "<if test='gnum != null'>gnum = #{gnum},</if>",
            "<if test='gprice != null'>gprice = #{gprice},</if>",
            "<if test='status != null'>status = #{status}</if>",
            "</set>",
            "where gid = #{gid}", // 假设你有一个id字段来定位要更新的记录
            "</script>"
    })
    boolean updateGoods(goods goods);
}
