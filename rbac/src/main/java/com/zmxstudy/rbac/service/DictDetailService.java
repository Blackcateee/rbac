package com.zmxstudy.rbac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zmxstudy.rbac.entity.DictDetail;

import java.util.List;

/**
* @author star
* @description 针对表【dict_detail(数据字典详情)】的数据库操作Service
* @createDate 2024-04-07 16:14:35
*/
public interface DictDetailService extends IService<DictDetail> {
    /**
     * 根据字典名称获取字典数据
     * @param dictname  字典名称获
     * @return  字典数据
     */
    List<DictDetail> findDictDetails(String dictname);
}
