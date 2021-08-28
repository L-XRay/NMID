package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class LoginController {
    @RequestMapping("main")
    public String main(){
        return "main";
    }
    @RequestMapping("/Login")
    public String login(){
        return "login";
    }
    @RequestMapping ("/login")
    public String login(HttpSession session, String username, String password, Model model){
        //把用户信息存在session中
        session.setAttribute("usernameInfo",username);
        model.addAttribute("username",username);
        return "main";
    }
    @RequestMapping ("/GOOut")
    public String login(HttpSession session){
        session.removeAttribute("usernameInfo");
        return "main";
    }
}
