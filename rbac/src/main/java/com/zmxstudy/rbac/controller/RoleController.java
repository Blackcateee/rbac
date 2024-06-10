package com.zmxstudy.rbac.controller;

import com.zmxstudy.rbac.base.BaseController;
import com.zmxstudy.rbac.entity.Auth;
import com.zmxstudy.rbac.entity.Role;
import com.zmxstudy.rbac.entity.User;
import com.zmxstudy.rbac.service.RoleService;
import com.zmxstudy.rbac.vo.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author star
 */
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController<RoleService, Role> {
    @PutMapping("edit/auths")
    public boolean editRoles(@RequestHeader("roleId") Long roleId, @RequestBody List<Long> authIds) {
        return baseService.editRoles(roleId, authIds);
    }

    @GetMapping("/roles")
    public List<Role> getRoles(@RequestHeader("username") String username) {
        return baseService.getRoles(username);
    }

    @PostMapping("/addRole")
    public boolean addRole(@RequestHeader("username") String username, Role role) {
        return baseService.addRole(role, role.getAuthIds());
    }

    @PostMapping("/deleteRole")
    public boolean deleteRole(@RequestHeader("username") String username, @RequestHeader("roleId") Long roleId) {
        return baseService.deleteRole(roleId);
    }
}
