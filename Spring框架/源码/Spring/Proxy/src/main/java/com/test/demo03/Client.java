package com.test.demo03;

public class Client {
    public static void main(String[] args) {
        //真实角色
        Landlord landlord=new Landlord();

        //代理角色
        ProxyInvocationHandler proxyInvocationHandler=new ProxyInvocationHandler();
        //通过调用程序处理角色 ProxyInvocationHandler 来处理调用的接口对象
        proxyInvocationHandler.setRenter(landlord);

        Renter proxy=(Renter) proxyInvocationHandler.getProxy();//这里的proxy就是自动生成的

        proxy.rent();
    }
}
