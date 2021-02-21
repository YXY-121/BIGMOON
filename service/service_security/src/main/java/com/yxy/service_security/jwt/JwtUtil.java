package com.yxy.service_security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yxy.service_security.bean.ServiceUser;


import java.io.UnsupportedEncodingException;
import java.util.Date;

public class JwtUtil {
    //过期时间五分钟
    private  static final long EXPIRE_TIME = 5*60*1000;

    //匹配密码 认证token里的username和password是否一样        ——————————JWT.require()
    //这个方法应该是用在redis里把
    public static boolean verify(String token,String userId,String password,String majorName,String schoolName){
        try{
            Algorithm algorithm=Algorithm.HMAC256(password);
            JWTVerifier verifier= JWT.require(algorithm)
                    .withClaim("userId",userId)
                    .withClaim("schoolName",schoolName)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
    }

    //解码token，获取其中的username   ——————————JWT.decode()
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userId").asString();
        } catch (JWTDecodeException e){
            return null;
        }
    }
    public static String getSchoolName(String token){
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("schoolName").asString();
        } catch (JWTDecodeException e){
            return null;
        }
    }

    public static ServiceUser getUser(String token){
        try {
            DecodedJWT jwt = JWT.decode(token);

            String schoolName = jwt.getClaim("schoolName").asString();
            String userId = jwt.getClaim("userId").asString();
            ServiceUser user=new ServiceUser();
            user.setUserId(userId);
            user.setSchoolName(schoolName);
            return user;
        } catch (JWTDecodeException e){
            return null;
        }
    }

    //生成token        ——————————JWT.create()
    public static String sign(String userId,String password,String schoolName){
        try{
            //根据现在的时间，设置过期时间
            Date date=new Date(System.currentTimeMillis()+EXPIRE_TIME);

            //HMAC256加密（类似上锁）

            Algorithm algorithm=Algorithm.HMAC256(password);
            //返回新生成的token
            return JWT.create()
                    .withClaim("userId",userId)//让token里带上username的字段
                    .withClaim("schoolName",schoolName)
                    .withExpiresAt(date)//带上过期时间
                    .sign(algorithm);//最后用保密机制包住
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
