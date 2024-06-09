package com.zmxstudy.rbac.controller;

import com.zmxstudy.rbac.base.BaseController;
import com.zmxstudy.rbac.entity.Auth;
import com.zmxstudy.rbac.service.AuthService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author star
 */
@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController<AuthService, Auth> {

}
