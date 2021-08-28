package com.test.diy;

//方式三:注解实现AOP

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect  //标注这个类是一个切面
public class AnnotationPointCut {
    @Before("execution(* com.test.service.UserServiceImpl.*(..))")
    public void Before(){
        System.out.println("方法执行前....");
    }
    @After("execution(* com.test.service.UserServiceImpl.*(..))")
    public void After(){
        System.out.println("方法执行后....");
    }

    //在环绕增强中,我们可以给定一个参数,代表我们要处理切入的点
    @Around("execution(* com.test.service.UserServiceImpl.*(..))")
    public void Around(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("环绕前");
        System.out.println("signature:"+jp.getSignature());//获取签名
        //执行方法
        Object proceed=jp.proceed();
        System.out.println("环绕后");
        System.out.println(proceed);
    }
}
