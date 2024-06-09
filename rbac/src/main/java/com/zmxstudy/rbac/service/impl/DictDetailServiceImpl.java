package com.zmxstudy.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zmxstudy.rbac.entity.DictDetail;
import com.zmxstudy.rbac.service.DictDetailService;
import com.zmxstudy.rbac.mapper.DictDetailMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
* @author star
* @description 针对表【dict_detail(数据字典详情)】的数据库操作Service实现
* @createDate 2024-04-07 16:14:35
*/
@Service
public class DictDetailServiceImpl extends ServiceImpl<DictDetailMapper, DictDetail>
    implements DictDetailService{

    @Override
    public List<DictDetail> findDictDetails(String dictname) {
        if (Objects.isNull(dictname)){
            throw new RuntimeException("字典名称不能为空");
        }
        return baseMapper.listAllByDictname(dictname);
    }
}




