package com.test.State;

//模拟网络延时:放大问题的发生性
public class TestSleep implements Runnable{

    //票数
    private int tickets=10;

    @Override
    public void run() {
        while(true){
            if(tickets<=0) {
                break;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName()+"拿到了第"+tickets--+"张票");
        }

    }

    public static void main(String[] args) {
        TestSleep testSleep=new TestSleep();

        new Thread(testSleep,"Ray").start();
        new Thread(testSleep,"JOJO").start();
        new Thread(testSleep,"Boss").start();
    }
}
