package com.test.data.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {


    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource DruidDataSource(){
        return  new DruidDataSource();
    }

    //后台监控:web.xml
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean<StatViewServlet> bean=new ServletRegistrationBean<>(new StatViewServlet(),"/druid/*");
        //后台需要登录，账号密码设置
        HashMap<String,String> initParameters=new HashMap<>();

        //增加配置
        initParameters.put("loginUsername","JOJO"); //登录的key是固定的 loginUsername loginPassword
        initParameters.put("loginPassword","123456");

        //允许谁访问
        initParameters.put("allow","");

        //禁止谁访问
       // initParameters.put("133","192.168.11.123");  用户名 ip

        bean.setInitParameters(initParameters);//设置初始化参数
        return bean;
    }

    //过滤器
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean=new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());

        //过滤那些请求
        Map<String,String> initParameters=new HashMap<>();
        //不进行统计
        initParameters.put("exclusions","*.js,*.css,/druid/*");
        return bean;
    }
}
