package com.test.swagger.controller;

import com.test.swagger.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping( "/hello")
    public String hello(){
        return "hello,world";
    }

    //只要接口中的返回值存在实体类，就会被扫描到swagger
    @PostMapping("/user")
    public User user(){
        return new User();
    }
}
