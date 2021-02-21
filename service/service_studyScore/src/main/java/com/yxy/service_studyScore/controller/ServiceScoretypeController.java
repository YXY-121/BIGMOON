package com.yxy.service_studyScore.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yxy.service_studyScore.service.ServiceScoretypeService;
import com.zongce.serviceBase.resultCode.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yxy
 * @since 2020-12-07
 */
@RestController
//@RequestMapping("/service_studyScore/service-scoretype")
public class ServiceScoretypeController {

    @Autowired
    ServiceScoretypeService scoretypeService;


}

