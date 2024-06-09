package com.zmxstudy.rbac.controller;

import com.zmxstudy.rbac.base.BaseController;
import com.zmxstudy.rbac.entity.Dept;
import com.zmxstudy.rbac.service.DeptService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author star
 */
@RestController
@RequestMapping("/dept")
public class DeptController extends BaseController<DeptService, Dept> {

}
