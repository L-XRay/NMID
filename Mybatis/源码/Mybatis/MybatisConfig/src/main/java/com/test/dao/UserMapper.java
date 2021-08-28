package com.test.dao;

import com.test.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {


    //查询全部用户
    List<User> getUserList();
    //根据ID查询用户
    User getUserByID(int id);
    //增加一个用户
    int AddUser(User user);
    //修改一个用户
    int UpdateUser(User user);
    //删除一个用户
    int DeleteUser(int id);
}
