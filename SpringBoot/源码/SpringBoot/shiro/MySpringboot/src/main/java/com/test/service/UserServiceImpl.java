package com.test.service;

import com.test.mapper.UserMapper;
import com.test.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;
    @Override
    public User selectUserByName(String name) {
        return userMapper.selectUserByName(name);
    }
}
