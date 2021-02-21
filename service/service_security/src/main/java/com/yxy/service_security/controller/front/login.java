package com.yxy.service_security.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yxy.service_security.bean.ServiceUser;
import com.yxy.service_security.bean.loginVo;
import com.yxy.service_security.jwt.JwtToken;
import com.yxy.service_security.jwt.JwtUtil;
import com.yxy.service_security.service.ServiceUserService;
import com.zongce.serviceBase.resultCode.ResultUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@CrossOrigin
@RestController
@RequestMapping("service_security/login")
public class login {

    @Autowired
    ServiceUserService userService;
    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    @PostMapping("login")
    public ResultUtils login(@RequestBody loginVo loginvo,HttpServletResponse response){

             if(StringUtils.isEmpty(loginvo.getUserId()))
               {
                   response.setStatus(400);
                   return ResultUtils.error().message("用户名为空，请填写").code(500);
               }

               if(StringUtils.isEmpty(loginvo.getSchoolName())){
                   response.setStatus(400);
                       return ResultUtils.error().message("学校名为空，请填写").code(500);

               }
               if(StringUtils.isEmpty(loginvo.getPassword())){
                   response.setStatus(400);
                   return ResultUtils.error().message("密码为空，请填写").code(500);

               }

        //先拿token
        String token1 = JwtUtil.sign(loginvo.getUserId(),loginvo.getPassword(),loginvo.getSchoolName());

        Subject currentUser = SecurityUtils.getSubject();
        JwtToken token=new JwtToken(token1);

        try {
            currentUser.login(token);
            currentUser.isAuthenticated();


//if no exception, that's it, we're done!
        } catch ( UnknownAccountException uae ) {
            response.setStatus(400);
            return ResultUtils.error().message("该用户不存在").code(500);
//username找不到
        } catch ( IncorrectCredentialsException ice ) {
            response.setStatus(400);
//password 和username名字匹配不上
            return ResultUtils.error().message("密码错误").code(500);

        } catch ( LockedAccountException lae ) {
            response.setStatus(400);
            return ResultUtils.error().message("没输入用户名").code(500);
//username没输入
        }

        catch ( AuthenticationException ae ) {
//unexpected condition - error?
            response.setStatus(400);
            return ResultUtils.error().message("账号或密码错误").code(500);
        }
        String principal = (String)currentUser.getPrincipal();
        ServiceUser user = JwtUtil.getUser(principal);
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("schoolName",user.getSchoolName());
        wrapper.eq("userId",user.getUserId());
        user = userService.getOne(wrapper);

        //认证成功
        return ResultUtils.OK().data("客户端保存好token",token1).data("权限",user.getRoleId()
).code(200);
    }
    @RequiresRoles("admin")
    @GetMapping("admin")
    @RequiresAuthentication
    public ResultUtils admin(HttpServletRequest request){
        //检查是否登录了有没有token呗！
        request.getHeader("");

        return ResultUtils.OK().message("你好啊管理元");
    }
       @RequiresRoles("user")
      @GetMapping("user")
        public ResultUtils user(){
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()){
            return ResultUtils.OK().message("你好啊user");
        }
        return ResultUtils.OK().message("你好啊游客");



    }

}
