package com.test.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
public class Controller{
    @RequestMapping("/h1")
    public String H1(Model model){
        //封装数据
        model.addAttribute("msg","Hello,SpringMVC");
        return "hello";//返回视图位置
    }
    @RequestMapping("h2")
    public String H2(Model model){
        model.addAttribute("msg","Hello,master");
        return "hello";
    }
}
