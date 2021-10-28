package com.test.State;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class TestDaemon {
    public static void main(String[] args) {
        JOJO jojo= new JOJO();
        RAY ray=new RAY();

        Thread thread=new Thread(jojo);
        thread.setDaemon(true);  //默认为false 用户线程

        thread.start(); //JOJO启动
        new Thread(ray).start(); // RAY启动

        //虚拟机停止需要等待时间，此时守护线程JOJO仍继续跑
    }
}

class JOJO implements Runnable{
    @Override
    public void run() {
        while (true) {
            System.out.println("JOJO LOVE RAY！");
        }
    }
}

class RAY implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("RAY 很 开 心");
        }
        System.out.println("RAY LOVE JOJO");
    }
}
