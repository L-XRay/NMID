package com.test.config;

import com.test.config.pojo.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConfigApplicationTests {
    @Autowired//自动注入person
    private Person person;

    @Test
    void contextLoads() {
        System.out.println(person);
    }

}
