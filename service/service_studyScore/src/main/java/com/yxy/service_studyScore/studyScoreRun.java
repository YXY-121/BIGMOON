package com.yxy.service_studyScore;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = {"com.yxy"})
@EnableSwagger2
@EnableDiscoveryClient
@EnableDubbo

public class studyScoreRun {
    public static void main(String[] args) {
        SpringApplication.run(studyScoreRun.class, args);
    }

}
