package com.zongce.gateway.filter;


import com.zongce.gateway.JWT.JWTUtil;
import com.zongce.gateway.resultCode.ResultUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@CrossOrigin
@RestController
@RequestMapping("")
public class login {
   /* @Autowired
    HttpServletResponse response;*/
    @PostMapping("login")
    public ResultUtils login(@RequestBody user user) {
        if (user != null) {
            String id = user.getLoginName();
            String pwd = user.getPassword();
            if (id.equals("1") && pwd.equals("1")) {
                String token= JWTUtil.sign(id, pwd, "null");
                System.out.println("new login");
            //    response.addHeader("Authorization",token);
                return ResultUtils.OK().code(200).message(token);
            }
        }

        return ResultUtils.error().message("登录失败");


    }
}