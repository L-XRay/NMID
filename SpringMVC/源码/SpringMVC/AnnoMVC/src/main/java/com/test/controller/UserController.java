package com.test.controller;

import com.test.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.Name;

@Controller
public class UserController {
    @RequestMapping("/hello")
    public String hello(@RequestParam("username") String name){
        System.out.println(name);
        return "hello";
    }

    @RequestMapping("/user")
    public String User(User user){
        System.out.println(user);
        return "hello";
    }

    @GetMapping("/test")
    public String test(ModelMap model){
        model.addAttribute("msg","nice");
        return "hello";
    }
}
