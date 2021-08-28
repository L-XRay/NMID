package com.test.service;

import com.test.dao.UserDao;
import com.test.dao.UserDaoImpl;
import com.test.dao.UserDaoMySQLImpl;

import java.util.Set;

public class UserServiceImpl implements UserService{
    //private UserDao userDao=new UserDaoImpl;
    private UserDao userDao;
    //利用set进行动态值的注入
    public void setUserDao(UserDao userDao){
        this.userDao=userDao;
    }
    @Override
    public void GetUser() {
        userDao.GetUser();
    }
}
