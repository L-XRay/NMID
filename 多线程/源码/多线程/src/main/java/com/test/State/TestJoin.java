package com.test.State;

//可以想象为插队
//前100 VIP和main同时执行，当到了100时，VIP插队，main线程需等待其执行完后再执行
public class TestJoin implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("线程VIP:"+i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestJoin join=new TestJoin();
        Thread thread=new Thread(join);
        thread.start();

        for (int i = 0; i < 200; i++) {
            if(i==100){
                thread.join();
            }
            System.out.println("main:"+i);
        }
    }
}
