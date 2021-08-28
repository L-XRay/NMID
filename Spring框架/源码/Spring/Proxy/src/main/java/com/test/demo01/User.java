package com.test.demo01;

public class User {
    public static void main(String[] args) {
        //代理
        Proxy proxy=new Proxy(new Landlord());
        proxy.rent();
    }
}
