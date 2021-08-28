package com.test.utils;

import org.junit.Test;

import java.util.UUID;

public class IDUtils {

    //获取随机id
    public static String getID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    @Test
    public void test(){
        System.out.println(getID());
        System.out.println(getID());
        System.out.println(getID());
    }
}
