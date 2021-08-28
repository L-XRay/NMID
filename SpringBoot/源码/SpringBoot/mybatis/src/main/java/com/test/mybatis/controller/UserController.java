package com.test.mybatis.controller;

import com.test.mybatis.mapper.UserMapper;
import com.test.mybatis.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/userList")
    public List<User> selectUserList(){
        List<User> userList=userMapper.selectUserList();
        for (User user:userList){
            System.out.println(user);
        }
        return userList;
    }
}
