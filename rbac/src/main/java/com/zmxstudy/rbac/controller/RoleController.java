package com.zmxstudy.rbac.controller;

import com.zmxstudy.rbac.base.BaseController;
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
}
