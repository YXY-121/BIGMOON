package com.zongce.serviceBase.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import org.springframework.http.MediaType;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration//配置类
@EnableSwagger2 //swagger注解
public class SwaggerConfig {

/*    @Bean
    public Docket webApiConfig(){
        final String SWAGGER_SCAN_BASE_PACKAGE = "com.yxy.service_studyScore";

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select().apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE))
               // .apis(RequestHandlerSelectors.basePackage("com.zongce"))
                .paths(PathSelectors.any())
            *//*    .paths(Predicates.not(PathSelectors.regex("/admin/.*")))
                .paths(Predicates.not(PathSelectors.regex("/error.*")))*//*
                .build();

    }

    private ApiInfo webApiInfo(){

        return new ApiInfoBuilder()
                .title("网站-课程中心API文档")
                .description("本文档描述了课程中心微服务接口定义")
                .version("1.0")
                .contact(new Contact("java", "http://atguigu.com", "1123@qq.com"))
                .build();
    }*/
//@Bean
//public Docket webApiConfig(){
//    return new Docket(DocumentationType.SWAGGER_2)
//            .groupName("webApi")
//            .apiInfo(webApiInfo())
//            .select()
//             .apis(RequestHandlerSelectors.basePackage("com.yxy"))
//            .paths(Predicates.not(PathSelectors.regex("/admin/.*")))
//            .paths(Predicates.not(PathSelectors.regex("/error.*")))
//            .build();
//
//}
//
//    private ApiInfo webApiInfo(){
//
//        return new ApiInfoBuilder()
//                .title("网站-课程中心API文档")
//                .description("本文档描述了课程中心微服务接口定义")
//                .version("1.0")
//                .contact(new Contact("java", "http://atguigu.com", "1123@qq.com"))
//                .build();
//    }

    @Bean
    public Docket api(){
        //添加head参数start
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        tokenPar.name("x-access-token").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        //添加head参数end


        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/api/.*"))
                .build()
                .globalOperationParameters(pars)
                .apiInfo(apiInfo());
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("后台接口文档与测试")
                .description("这是一个给app端人员调用server端接口的测试文档与平台")
                .version("1.0.0")
                .termsOfServiceUrl("http://terms-of-services.url")
                //.license("LICENSE")
                //.licenseUrl("http://url-to-license.com")
                .build();
    }
}
