package com.zmxstudy.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zmxstudy.rbac.entity.Dept;
import com.zmxstudy.rbac.service.DeptService;
import com.zmxstudy.rbac.mapper.DeptMapper;
import org.springframework.stereotype.Service;

/**
* @author star
* @description 针对表【dept(部门)】的数据库操作Service实现
* @createDate 2024-04-03 15:37:14
*/
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept>
    implements DeptService{

}




