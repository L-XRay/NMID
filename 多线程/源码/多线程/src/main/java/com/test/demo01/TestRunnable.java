package com.test.demo01;

//创建线程方式2 :实现Runnable接口，重写run方法，执行线程需要丢入Runnable接口实现类，调用start方法
public class TestRunnable implements Runnable{
    @Override
    public void run() {
        //run方法线程体
        for(int i=1;i<=10;i++){
            System.out.println("Thread:"+i);
        }
    }
    public static void main(String[] args) {
        //main线程，主线程

        //创建Runnable接口的实现类对象
        TestRunnable runnable=new TestRunnable();

//        //创建线程对象，通过线程对象开启线程，代理
//        Thread thread=new Thread(runnable);
//        //调用start方法开启线程
//
//        thread.start();

        new Thread(runnable).start();
        for(int i=1;i<=100;i++){
            System.out.println("main:"+i);
        }
    }
}
