package com.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Configuration
public class MyLocaleResolver implements LocaleResolver {

    //解析请求
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {

        //获取请求中的语言参数链接
        String language=httpServletRequest.getParameter("language");

        Locale locale=Locale.getDefault();

        //如果请求连接不为空
        if(!StringUtils.isEmpty(language)){
            //zh_CN
            String split[]=language.split("_");

            //语言，国家
            locale=new Locale(split[0],split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
