package com.test.config;

import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //登录
        HttpSession session=request.getSession(false);//为空时，不自动创建
        if(session.getAttribute("usernameInfo")!=null|| request.getRequestURI().contains("Login")|| request.getRequestURI().contains("login")){
            return true;
        }
        //未登录
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,response);
        return false;
    }
}
