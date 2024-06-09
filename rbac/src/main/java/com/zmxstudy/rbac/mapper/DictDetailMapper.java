package com.zmxstudy.rbac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zmxstudy.rbac.entity.DictDetail;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author star
* @description 针对表【dict_detail(数据字典详情)】的数据库操作Mapper
* @createDate 2024-04-07 16:14:35
* @Entity com.zmxstudy.rbac.entity.DictDetail
*/
public interface DictDetailMapper extends BaseMapper<DictDetail> {
    @Select("SELECT `key`,`value` FROM dict LEFT JOIN dict_detail ON dict.id=dict_detail.dict_id WHERE dictname=#{dictname}")
    List<DictDetail> listAllByDictname(@Param("dictname") String dictname);
}




