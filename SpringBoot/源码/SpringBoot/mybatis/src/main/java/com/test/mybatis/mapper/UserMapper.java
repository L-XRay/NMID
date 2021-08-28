package com.test.mybatis.mapper;

import com.test.mybatis.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper //这个注解表示这是一个mybatis下的mapper 类 ：dao
@Repository
public interface UserMapper {
    List<User> selectUserList();

    User selectUserById(int id);

    int addUser(User user);

    int updateUser(User user);

    int deleteUser(int id);
}
