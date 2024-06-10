package com.zmxstudy.rbac.service;

import com.zmxstudy.rbac.entity.Auth;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author star
* @description 针对表【auth(系统菜单)】的数据库操作Service
* @createDate 2024-04-03 15:37:14
*/
public interface AuthService extends IService<Auth> {

    public List<Auth> getAuths();

    public boolean addAuth(Auth auth);

    public boolean deleteAuth(Long id);

    public boolean editAuth(Auth auth);
}
