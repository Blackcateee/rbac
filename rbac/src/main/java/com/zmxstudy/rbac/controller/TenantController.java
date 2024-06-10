package com.zmxstudy.rbac.controller;

import com.zmxstudy.rbac.base.BaseController;
import com.zmxstudy.rbac.entity.User;
import com.zmxstudy.rbac.entity.tenant;
import com.zmxstudy.rbac.service.TenantServise;
import com.zmxstudy.rbac.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tenant")
public class TenantController extends BaseController<TenantServise, tenant> {
    /**
     * 根据商户ID查询商户表所有字段
     *
     * @return 商户信息集合
     */
    @GetMapping("/all")
    public List<tenant> getTenantall() {
        return baseService.gettenants();
    }
    @GetMapping("/byid")
    public tenant getTenantByid(@RequestHeader("id") int id) {
        return baseService.gettenant(id);
    }
    /**
     * 根据商户ID删除商户
     *
     * @param id 商户ID
     * @return
     */
    @DeleteMapping("/delbyid")
    public boolean deleteTenantById(@RequestHeader("id") int id) {
        return baseService.deleteTenantById(id);
    }
    /**
     * 新增商户商户
     *
     * @param tenant 商户
     * @return
     */
    @PostMapping("/insert")
    public boolean insertTenant(@RequestBody tenant tenant) {
        return baseService.insertTenant(tenant);
    }
    /**
     * 修改商户商户
     *
     * @param tenant 商户
     * @return
     */
    @PostMapping("/update")
    public boolean updateTenant(@RequestBody tenant tenant) {
        return baseService.updateTenant(tenant);
    }
}
