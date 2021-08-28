package com.test.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableOpenApi //开启swagger
public class SwaggerConfig {

    @Bean
    public Docket docket1(){
        return new Docket(DocumentationType.OAS_30).groupName("A");
    }

    @Bean
    public Docket docket2(){
        return new Docket(DocumentationType.OAS_30).groupName("B");
    }

    @Bean
    public Docket docket3(){
        return new Docket(DocumentationType.OAS_30).groupName("C");
    }

    //配置swagger的docket bean实例
    @Bean
    public Docket docket(Environment environment){

        //设置要显示的swagger环境
        Profiles profiles=Profiles.of("dev","test");
        //通过 environment.acceptsProfiles 判断是否处在自己设定的环境中
        boolean flag=environment.acceptsProfiles(profiles);
        return new Docket(DocumentationType.OAS_30)
                .groupName("JOJO")
                .apiInfo(apiInfo())
                .enable(flag)//是否启用swagger
                .select()
                //RequestHandlerSelectors 配置要扫描接口的方式
                //basePackage:指定扫描的包
                //any:扫描全部
                //none:全不扫描
                //withClassAnnotation:扫描类上的注解 参数是一个注解的反射对象  @RestController.class
                //withMethodAnnotation:扫描方法上的注解  @GetMapping.class
                .apis(RequestHandlerSelectors.basePackage("com.test.swagger.controller"))
                .build();
    }

    //配置swagger信息
    private ApiInfo apiInfo(){
        Contact contact=new Contact("JOJO","https://www.cnblogs.com/godisagirl/","3058816256@qq.com");
        return new ApiInfo(
                "Swagger学习", // 标题
                "学习演示如何配置Swagger", // 描述
                "v1.0", // 版本
                "http://www.baidu.com", // 组织链接
                contact, // 联系人信息
                "Apach 2.0 许可", // 许可
                "许可链接", // 许可连接
                new ArrayList<>()// 扩展
        );
    }
}
