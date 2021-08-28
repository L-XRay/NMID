package com.test.log;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

import static java.lang.Thread.sleep;

public class After implements AfterReturningAdvice {
    //returnValue 返回值
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(method.getName()+"方法执行完毕");
        System.out.println("返回结果为:"+returnValue);
    }
}
