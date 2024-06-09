package com.zmxstudy.rbac.controller;

import com.zmxstudy.rbac.base.BaseController;
import com.zmxstudy.rbac.entity.Dict;
import com.zmxstudy.rbac.service.DictService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author star
 */
@RestController
@RequestMapping("/dict")
public class DictController extends BaseController<DictService, Dict> {

}
