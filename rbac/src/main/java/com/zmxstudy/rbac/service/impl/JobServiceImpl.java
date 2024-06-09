package com.zmxstudy.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zmxstudy.rbac.entity.Job;
import com.zmxstudy.rbac.service.JobService;
import com.zmxstudy.rbac.mapper.JobMapper;
import org.springframework.stereotype.Service;

/**
* @author star
* @description 针对表【job(岗位)】的数据库操作Service实现
* @createDate 2024-04-03 15:37:14
*/
@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, Job>
    implements JobService{

}




