package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @RequestMapping("/user/login")
    public String Login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model, HttpSession Session){
        if(!StringUtils.isEmpty(username)&&"123456".equals(password)){
            Session.setAttribute("LoginUser",username);
            return "redirect:/main.html";//重定向
        }
        else{
            model.addAttribute("msg","用户名或者密码错误");
            return "/index";
        }
    }

    @RequestMapping("/user/LoginOut")
    public String LoginOut(HttpSession Session){
        Session.invalidate();
        return "redirect:/index";
    }
}
