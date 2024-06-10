package com.zmxstudy.rbac.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zmxstudy.rbac.constant.SecurityConstant;
import com.zmxstudy.rbac.entity.Auth;
import com.zmxstudy.rbac.entity.Role;
import com.zmxstudy.rbac.entity.User;
import com.zmxstudy.rbac.mapper.UserMapper;
import com.zmxstudy.rbac.service.UserService;
import com.zmxstudy.rbac.util.RedisUtil;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

/**
 * @author star
 * @description 针对表【user(系统用户)】的数据库操作Service实现
 * @createDate 2024-04-03 15:37:14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Resource
    public PasswordEncoder passwordEncoder;

    @Resource
    public RedisUtil redisUtil;

    @Override
    public User getUser(String username) {
        return getOne(new QueryWrapper<User>().eq("username", username));
    }

    @Override
    public List<Role> findRoles(String username) {
        Long userId = baseMapper.selectIdByUsername(username);
        if (ObjectUtil.isEmpty(userId)) {
            return Collections.emptyList();
        }
        return baseMapper.listRolesByUserId(userId);
    }

    @Override
    public List<Auth> findAuths(String username) {
        Long userId = baseMapper.selectIdByUsername(username);
        List<Long> roleIds = baseMapper.listRoleIdsByUserId(userId);
        if (ObjectUtil.isEmpty(roleIds)) {
            return Collections.emptyList();
        }
        return baseMapper.listAuthsByRoleIds(roleIds);
    }

    @Override
    public boolean editRoles(String username, List<Long> roleIds) {
        if (ObjectUtil.isEmpty(roleIds)) {
            return false;
        }
        Long userId = baseMapper.selectIdByUsername(username);
        if (ObjectUtil.isEmpty(userId)) {
            return false;
        }
        baseMapper.deleteRolesByUserId(userId);
        baseMapper.insertRolesBatch(userId, roleIds);
        return true;
    }

    @Override
    public boolean register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // 会员注册,需要添加会员角色
        baseMapper.insertUser(user);
        Long id = baseMapper.selectIdByUsername(user.getUsername());
        // 1:管理员 2：会员 3：商家
        baseMapper.insertRolesBatch(id, user.getRoleId());
        return true;
    }

    @Override
    public boolean editUser(User user) {
        if (StringUtils.isNotBlank(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return baseMapper.editUser(user);
    }

    @Override
    public String avatarUpload(String userName, MultipartFile file) throws Exception{
        InputStream inputStream = file.getInputStream();
        File avatar = new File("D:\\workspace\\rbac\\rbac\\src\\main\\resources\\static\\upload\\avatar\\" + userName + ".jpg");
        FileOutputStream fileOutputStream = new FileOutputStream(avatar);
        fileOutputStream.write(inputStream.readAllBytes());
        fileOutputStream.close();
        User user = new User();
        user.setUsername(userName);
        user.setAvatarPath("rbac/rbac/src/main/resources/static/upload/avatar/" + userName + ".jpg");
        baseMapper.editUser(user);
        return "rbac/rbac/src/main/resources/static/upload/avatar/" + userName + ".jpg";
    }

    @Override
    public boolean logout(String username) {
        redisUtil.delete(SecurityConstant.TOKEN_KEY);
        return true;
    }
}




