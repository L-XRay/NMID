package com.test.demo01;

//创建线程方式一:继承Thread类，重写run方法，调用start开启线程
//线程开启不一定立即执行，由CPU调度执行
public class TestThread extends Thread{
    @Override
    public void run() {
        //run方法线程体
        for(int i=1;i<=10;i++){
            System.out.println("Thread:"+i);
        }
    }

    public static void main(String[] args) {
        //main线程，主线程

        //创建线程对象
        TestThread thread=new TestThread();

        //调用start方法开启线程
        thread.start();

        for(int i=1;i<=100;i++){
            System.out.println("main:"+i);
        }
    }
}

