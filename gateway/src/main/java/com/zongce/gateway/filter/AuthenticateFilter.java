package com.zongce.gateway.filter;

import com.zongce.gateway.JWT.JWTUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
@Configuration

public class AuthenticateFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //如果是登录页面就直接放行 其他的要检查
        return chain.filter(exchange);
//
//        System.out.println(exchange.getRequest().getPath());
//        if(exchange.getRequest().getPath().equals("/**/login"))
//        {
//
//        }
//
//          HttpHeaders header= exchange.getRequest().getHeaders();
//        ServerHttpResponse response = exchange.getResponse();
//
//        boolean authorization = header.containsKey("Authorization");
//        //存在就是已经登陆了
//        if (authorization){
//           String token= header.getFirst("Authorization");
//           System.out.println(token);
//            //token肯定只有一个 所以就是第一个
//
//
//            boolean verify = JWTUtil.verify(token, JWTUtil.getUsername(token), JWTUtil.getPassword(token),JWTUtil.getRole(token));
//
//            //如果认证过了就说明已经登录过了而且是正确的 放行
//            if(verify){
//                System.out.println("放行");
//                return   chain.filter(exchange);
//
//            }
//
//            //verify错了就说被破解了抛错误
//            System.out.println("token有问题");
//            response.setStatusCode(HttpStatus.UNAUTHORIZED);//401
//            //11. 返回
//            return response.setComplete();
//
//        }
//        //不存在就是还没有登录 跳转到login
//        else{
//
//            System.out.println("没有登录");
//            String url = "/login";
//            //303状态码表示由于请求对应的资源存在着另一个URI，应使用GET方法定向获取请求的资源
//            response.setStatusCode(HttpStatus.SEE_OTHER);//302
//            response.getHeaders().set(HttpHeaders.LOCATION, url);
//            return response.setComplete();
//
//        }
//
//


    }
}
