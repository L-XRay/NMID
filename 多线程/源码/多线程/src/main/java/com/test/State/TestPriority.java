package com.test.State;

public class TestPriority {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+"——>"+Thread.currentThread().getPriority());

        MyThread myThread=new MyThread();
        Thread thread1=new Thread(myThread);
        Thread thread2=new Thread(myThread);
        Thread thread3=new Thread(myThread);
        Thread thread4=new Thread(myThread);
        Thread thread5=new Thread(myThread);

        //需在线程启动前指定优先级；优先级只是让概率变高，并不会直接影响线程调度（等于没用？，还是得看我CPU大哥）
        thread1.setPriority(1);
        thread1.start();
        thread2.setPriority(2);
        thread2.start();
        thread3.setPriority(Thread.MIN_PRIORITY);
        thread3.start();
        thread4.setPriority(4);
        thread4.start();
        thread5.setPriority(Thread.MAX_PRIORITY);
        thread5.start();
    }
}

class MyThread implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"——>"+Thread.currentThread().getPriority());
    }
}
