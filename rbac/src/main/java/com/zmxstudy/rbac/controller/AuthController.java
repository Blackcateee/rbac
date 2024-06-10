package com.zmxstudy.rbac.controller;

import com.zmxstudy.rbac.base.BaseController;
import com.zmxstudy.rbac.entity.Auth;
import com.zmxstudy.rbac.service.AuthService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author star
 */
@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController<AuthService, Auth> {

    @GetMapping("/getAuths")
    public List<Auth> getAuths(@RequestHeader("username") String username) {
        return baseService.getAuths();
    }

    @PostMapping("/addAuth")
    public boolean addAuth(@RequestHeader("username") String username, @RequestBody Auth auth) {
        return baseService.addAuth(auth);
    }

    @PostMapping("/delAuth")
    public boolean delAuth(@RequestHeader("username") String username,@RequestHeader("id") Long id) {
        return baseService.deleteAuth(id);
    }

    @PostMapping("/editAuth")
    public boolean editAuth(@RequestHeader("username") String username, @RequestBody Auth auth) {
        return baseService.editAuth(auth);
    }
}
