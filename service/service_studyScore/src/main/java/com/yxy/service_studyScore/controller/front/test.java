package com.yxy.service_studyScore.controller.front;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zongce.serviceBase.resultCode.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Generated;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("service_studyScore/")
@CrossOrigin
public class test {

    @Autowired
    private HttpServletRequest request;
  //  @RequestMapping ("test")
    public ResultUtils gateway(){

      //  HttpHeaders header= exchange.getRequest().getHeaders();

        String token= request.getHeader("Authorization");


      //  boolean authorization = header.containsKey("Authorization");
        System.out.println(token);

        //return ResultUtils.OK().message("主页面").data("用户",.getUsername(token));
        return  ResultUtils.error();
    }
}
