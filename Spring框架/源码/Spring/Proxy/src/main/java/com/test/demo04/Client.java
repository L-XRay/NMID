package com.test.demo04;

import com.test.demo02.UserService;
import com.test.demo02.UserServiceImpl;
import com.test.demo03.Landlord;
import com.test.demo03.Renter;

public class Client {
    public static void main(String[] args) {
        //真实角色
        UserServiceImpl userService=new UserServiceImpl();

        //代理角色
        ProxyInvocationHandler proxyInvocationHandler=new ProxyInvocationHandler();
        //通过调用程序处理角色 ProxyInvocationHandler 来处理调用的接口对象
        proxyInvocationHandler.setTarget(userService);

        UserService proxy=(UserService) proxyInvocationHandler.getProxy();//这里的proxy就是自动生成的

        proxy.insert();
    }
}
