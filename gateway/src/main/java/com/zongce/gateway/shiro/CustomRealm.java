package com.zongce.gateway.shiro;

import com.zongce.gateway.JWT.JWTUtil;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


public class CustomRealm  {
//extends AuthorizingRealm

  /*  @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {//授权的principal
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        String roleId=null;
        //获得身份标识
        String token =(String) principalCollection.getPrimaryPrincipal();


        simpleAuthorizationInfo.addRole(roleId);

        return simpleAuthorizationInfo;
    }


    @Override//认证的token
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取token
        String token = (String)authenticationToken.getPrincipal();

        //从token中解密获得id、school、 major
        String username = JWTUtil.getUsername(token);
        String schoolName = JWTUtil.getSchoolName(token);

        if(StringUtils.isEmpty(username)||StringUtils.isEmpty(schoolName))
        {
       //     throw new zongCeException("token错误");
        }
        //从数据库里检查有没有这个人,有就带上密码返回。
     //   ServiceUser serviceUser = userService.IsExistUser(username, schoolName);

        //如果该用户不存在
//        if(serviceUser==null){
//            return null;
//        }

        //检查关键信息有没有被动过手脚 id、school、major
      //  JWTUtil.verify(token,serviceUser.getUserId(),serviceUser.getPassword(),serviceUser.getMajorName(),serviceUser.getSchoolName());


        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(token,token,"myrealm");

        return simpleAuthenticationInfo;
    }
//支持JWToken
    @Override
    public boolean supports(AuthenticationToken token) {
       return token instanceof JWTUtil;
    }
*/}
