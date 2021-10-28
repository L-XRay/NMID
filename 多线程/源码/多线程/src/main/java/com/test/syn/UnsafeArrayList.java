package com.test.syn;

// 多个线程看到的是同一片内存，造成复用
import java.util.ArrayList;
import java.util.List;

public class UnsafeArrayList {
    public static void main(String[] args) {
        List<String> list=new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            new Thread(()->{
                synchronized (list) {
                    list.add(Thread.currentThread().getName());
                }
            }).start();   //lamda
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(list.size());
    }
}
