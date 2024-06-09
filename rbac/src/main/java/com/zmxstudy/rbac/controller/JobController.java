package com.zmxstudy.rbac.controller;

import com.zmxstudy.rbac.base.BaseController;
import com.zmxstudy.rbac.entity.Job;
import com.zmxstudy.rbac.service.JobService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author star
 */
@RestController
@RequestMapping("/job")
public class JobController extends BaseController<JobService, Job> {

}
