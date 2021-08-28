package com.test.demo04;

import com.test.demo03.Renter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static java.lang.Thread.sleep;

//用这个类自动生成代理类
public class ProxyInvocationHandler implements InvocationHandler {

    //被代理的接口
    private Object target;
    public void setTarget(Object target) {
        this.target = target;
    }

    //生成得到代理类
    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }

    //处理代理实例，并返回结果
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //动态代理的本质就是使用反射机制实现
        Object result=method.invoke(target,args);
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ing(method.getName());
        try {
            sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log(method.getName());
        return result;
    }

    //日志方法
    public void ing(String msg){
        System.out.println("正在"+msg+"用户"+"…………");
    }

    public void log(String msg){
        System.out.println("已"+msg+"该用户");
    }
}
