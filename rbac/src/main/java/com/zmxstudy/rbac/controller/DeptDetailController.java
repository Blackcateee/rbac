package com.zmxstudy.rbac.controller;

import com.zmxstudy.rbac.base.BaseController;
import com.zmxstudy.rbac.entity.DictDetail;
import com.zmxstudy.rbac.service.DictDetailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author star
 */
@RestController
@RequestMapping("/dept/detail")
public class DeptDetailController extends BaseController<DictDetailService, DictDetail> {
    @GetMapping("/name/{dictname}")
    public List<DictDetail> findDictDetails(@PathVariable("dictname") String dictname){
        return baseService.findDictDetails(dictname);
    }
}
