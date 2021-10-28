package com.test.State;

//礼让就是让应执行的线程退回就绪状态，即与重新争取执行权
//礼让不一定成功，具有随机性，决定权在CPU
public class TestYield {

    public static void main(String[] args) {
        Yield yield=new Yield();

        new Thread(yield,"A").start();
        new Thread(yield,"B").start();
    }
}
class Yield implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"开始执行");
        if(Thread.currentThread().getName()=="A"){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"结束执行");
    }
}
