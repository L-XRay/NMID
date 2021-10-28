package com.test.demo01;

import java.util.Objects;

public class Race implements Runnable{

    private static String winner;

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            if (Objects.equals(Thread.currentThread().getName(), "兔子") &&i%10==0) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            boolean flag = over(i);
            if (flag) {
                break;
            }
            System.out.println(Thread.currentThread().getName() + "走了" + i + "步");
        }
    }

    private boolean over(int steps){
        if(winner!=null){
            return true;
        }
        if(steps==100){
            winner=Thread.currentThread().getName();
            System.out.println("winner is "+winner);
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
        Race race=new Race();

        new Thread(race,"兔子").start();
        new Thread(race,"乌龟").start();
    }

}


