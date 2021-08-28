package com.test.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//等价于  <bean id="user" class="com.test.pojo.User"/>
//@Component组件
@Component
public class User {

    public String name;
    // 相当于  <property name="name" value="JOJO"/>
    @Value("JOJO")
    public void setName(String name) {
        this.name = name;
    }
}
