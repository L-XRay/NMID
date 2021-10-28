package com.test.Lock;

import java.util.concurrent.locks.ReentrantLock;

public class TestLock {
    public static void main(String[] args) {
        Ticket ticket=new Ticket();

        new Thread(ticket,"JOJO").start();
        new Thread(ticket,"Ray").start();
        new Thread(ticket,"X").start();
    }
}

class Ticket implements Runnable{
    int tickets=10;

    //定义可重入锁ReentrantLock
    private ReentrantLock lock=new ReentrantLock();

    @Override
    public void run() {
        while(true){
            try {
                //加锁
                lock.lock();
                if(tickets>0){
                    System.out.println(Thread.currentThread().getName()+"拿到第"+tickets--+"张票");
                }
                else{
                   // System.out.println("没有票了");
                    break;
                }
            }
            finally {
                //解锁
                lock.unlock();
            }
        }
    }
}
