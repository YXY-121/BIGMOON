package com.yxy.service_self;


import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/*在dao层的interface上面加上org.apache.ibatis.annotations.Mapper注解就可以了，但是我一直报错。这个应该是springboot整合mybatis的一个bug（新版的）
Description:
Field userDao in com.yuanqiao.service.impl.UserServiceImpl required a bean of type 'com.yuanqiao.dao.UserDao' that could not be found.
The injection point has the following annotations:
- @org.springframework.beans.factory.annotation.Autowired(required=true)*/
@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages="com.yxy")
@EnableDiscoveryClient//在客户端微服务启动类中添加注解
@EnableDubbo
@MapperScan(basePackages = "com.yxy.service_self.mapper")
public class self {
    public static void main(String[] args)
    {
        SpringApplication.run(self.class, args);
    }
}
