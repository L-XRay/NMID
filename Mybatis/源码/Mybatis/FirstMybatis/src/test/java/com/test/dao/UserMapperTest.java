package com.test.dao;

import com.test.pojo.User;
import com.test.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.*;

public class UserMapperTest {
    @Test
    public void getUserListTest(){
        SqlSession session= MybatisUtils.getSqlSession();
        UserMapper userMapper=session.getMapper(UserMapper.class);
        List<User> userList=userMapper.getUserList();
        for (User user : userList) {
            System.out.println(user);
        }
        session.close();
    }

    @Test
    public void getUserListTest2(){
        SqlSession session= MybatisUtils.getSqlSession();
        UserMapper userMapper=session.getMapper(UserMapper.class);
        List<User> userList=userMapper.getUserList2("a");
        for (User user : userList) {
            System.out.println(user);
        }
        session.close();
    }

    @Test
    public void getUserByIDTest(){
        SqlSession session= MybatisUtils.getSqlSession();
        UserMapper userMapper=session.getMapper(UserMapper.class);
        User user=userMapper.getUserByID(2);
        System.out.println(user);
        session.close();
    }

    @Test
    public void AddUserTest(){
        SqlSession session= MybatisUtils.getSqlSession();
        UserMapper userMapper=session.getMapper(UserMapper.class);
        int res=userMapper.AddUser(new User(1,"XIA","115647"));
        if(res>0){
            session.commit();//提交事务
            System.out.println("添加成功！");
        }

        session.close();
    }

    @Test
    public void AddUserTest2(){
        SqlSession session= MybatisUtils.getSqlSession();
        UserMapper userMapper=session.getMapper(UserMapper.class);
        Map<String,Object> map=new HashMap<String, Object>();

        map.put("userid",4);
        map.put("username","夏");
        map.put("pwd","335945");

        userMapper.AddUser2(map);
        session.commit();
        session.close();
    }

    @Test
    public void UpdateUserTest(){
        SqlSession session= MybatisUtils.getSqlSession();
        UserMapper userMapper=session.getMapper(UserMapper.class);
        userMapper.UpdateUser(new User(4,"XIA","115647"));
        session.commit();//提交事务
        session.close();
    }

    @Test
    public void DeleteUserTest(){
        SqlSession session= MybatisUtils.getSqlSession();
        UserMapper userMapper=session.getMapper(UserMapper.class);
        userMapper.DeleteUser(4);
        session.commit();
        System.out.println("删除成功！");
        session.close();
    }
}
