package com.zmxstudy.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zmxstudy.rbac.entity.Auth;
import com.zmxstudy.rbac.service.AuthService;
import com.zmxstudy.rbac.mapper.AuthMapper;
import org.springframework.stereotype.Service;

/**
* @author star
* @description 针对表【auth(系统菜单)】的数据库操作Service实现
* @createDate 2024-04-03 15:37:14
*/
@Service
public class AuthServiceImpl extends ServiceImpl<AuthMapper, Auth>
    implements AuthService{



}




