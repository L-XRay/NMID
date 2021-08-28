package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RestfulController {
    @GetMapping("/add/{a}/{b}")
    public String add1(@PathVariable(required = false) int a, @PathVariable(required = false) int b, Model model){
        int res=a+b;
        model.addAttribute("msg","add1结果为:"+res);
        return "/hello";
    }
    @PostMapping ("/add/{a}/{b}")
    public String add2(@PathVariable(required = false) int a, @PathVariable(required = false) int b, Model model){
        int res=a+b;
        model.addAttribute("msg","add2结果为:"+res);
        return "/hello";
    }
}
