package com.test.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class JDBCController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/userList")
    //查询数据表的所有信息
    //没有实体类，用万能的map获取
    public List<Map<String,Object>> userList(){
        String sql="select * from user";
        List<Map<String,Object>> mapList=jdbcTemplate.queryForList(sql);
        return  mapList;
    }

    @GetMapping("/addUser")
    public String addUser(){
        String sql="insert into mybatis.user(id,name,password) values (5,'xxx','123456')";
        jdbcTemplate.update(sql);
        return "add_Ok";
    }

    @GetMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") int id){
        String sql="update mybatis.user set name=?,password=? where id="+id;
        //封装
        Object[] objects=new Object[2];
        objects[0]="yyy";//user
        objects[1]="335067";//password
        jdbcTemplate.update(sql,objects);
        return "update_Ok";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") int id){
        String sql="delete from mybatis.user where id=?";
        jdbcTemplate.update(sql,id);
        return "delete_Ok";
    }
}
