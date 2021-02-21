package com.yxy.service_security.bean;


import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.yxy.service_security.exception.zongCeException;
import com.yxy.service_security.jwt.JwtToken;
import com.yxy.service_security.jwt.JwtUtil;
import com.yxy.service_security.service.ServiceUserService;
import lombok.SneakyThrows;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import java.util.Set;

//@Component
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    ServiceUserService userService;
    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {//授权的principal
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        String roleId=null;
        //获得身份标识
        String token =(String) principalCollection.getPrimaryPrincipal();

        /*//先在redis里判断有没有这个人的信息，如果没有才去mysql里找这个人
        ServiceUser redisUser=(ServiceUser)redisTemplate.opsForValue().get(JwtUtil.getUsername(token)+JwtUtil.getSchoolName(token));
        if(redisUser!=null){
            roleId = redisUser.getRoleId();
            System.out.println("redis里");
        }
        else
        {
            //从token里获得user的全部详细信息 并且存到redis里
            System.out.println("数据库里");
         //   redisTemplate.opsForValue().set(JwtUtil.getUsername(token)+JwtUtil.getSchoolName(token),serviceUser);
        }*/
        ServiceUser serviceUser = userService.IsExistUser(JwtUtil.getUsername(token), JwtUtil.getSchoolName(token));
        roleId = serviceUser.getRoleId();
        simpleAuthorizationInfo.addRole(roleId);

        return simpleAuthorizationInfo;
    }

    @SneakyThrows
    @Override//认证的token
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取token
        String token = (String)authenticationToken.getPrincipal();

        //从token中解密获得id、school、 major
        String username = JwtUtil.getUsername(token);
        String schoolName = JwtUtil.getSchoolName(token);

        if(StringUtils.isEmpty(username)||StringUtils.isEmpty(schoolName))
        {
            throw new zongCeException("token错误");
        }
        //从数据库里检查有没有这个人,有就带上密码返回。
        ServiceUser serviceUser = userService.IsExistUser(username, schoolName);
        System.out.println(serviceUser);

        //如果该用户不存在
        if(serviceUser==null){
            return null;
        }

        //检查关键信息有没有被动过手脚 id、school、major
            JwtUtil.verify(token,serviceUser.getUserId(),serviceUser.getPassword(),serviceUser.getMajorName(),serviceUser.getSchoolName());


        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(token,token,"myrealm");

        return simpleAuthenticationInfo;
    }
//支持JWToken
    @Override
    public boolean supports(AuthenticationToken token) {
       return token instanceof JwtToken;
    }
}
