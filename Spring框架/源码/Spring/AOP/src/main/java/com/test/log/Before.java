package com.test.log;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

import static java.lang.Thread.sleep;

public class Before implements MethodBeforeAdvice {

    //method:要执行的目标对象的方法
    //args:参数
    //target:目标对象
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("执行"+target.getClass().getName()+"的"+method.getName()+"方法"+"…………");
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
