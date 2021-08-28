package com.test.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.test.pojo.User;
import com.test.utils.JSONUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class UserController {

    @RequestMapping( "/json1")
    //@ResponseBody//返回真实返回的东西，一般是字符串，实现前后端分离，不会走视图解析器
    public String json1() throws JsonProcessingException {

        //输出对象
//        ObjectMapper mapper=new ObjectMapper();
        User user=new User("JOJO",20,"男");
//        String str=mapper.writeValueAsString(user);
        return JSONUtils.GetJSON(user);

    }

    @RequestMapping( "/json2")
    public String json2() throws JsonProcessingException {

        //输出集合
//        ObjectMapper mapper=new ObjectMapper();
        User user1=new User("JOJO",20,"男");
        User user2=new User("Ray",19,"男");
        User user3=new User("Three",18,"女");
        List<User> userList=new ArrayList<User>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
//        String str=mapper.writeValueAsString(userList);
        return JSONUtils.GetJSON(userList);

    }

    @RequestMapping( "/json3")
    public String json3() throws JsonProcessingException {
//
//        //输出时间对象
//        ObjectMapper mapper=new ObjectMapper();
//        // objectMapper,时间解析后的默认格式为: Timestamp, 时间裁
//
//        //自定义日期格式对象
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        Date date=new Date();
//        String str=mapper.writeValueAsString(sdf.format(date));
//        return str;
          Date date=new Date();
          return JSONUtils.GetJSON(date,"yyyy-MM-dd HH:mm:ss");
    }

    @RequestMapping("/fastjson")
    public String json4(){
        User user1=new User("JOJO",20,"男");
        User user2=new User("Ray",19,"男");
        User user3=new User("Three",18,"女");
        List<User> userList=new ArrayList<User>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        String str= JSON.toJSONString(userList);
        return JSONUtils.GetJSON(userList);
    }
}
