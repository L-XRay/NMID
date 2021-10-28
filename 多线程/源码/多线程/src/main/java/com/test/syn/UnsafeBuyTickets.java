package com.test.syn;

//不安全的买票
//会造成多人买同一张票的情况
public class UnsafeBuyTickets {
    public static void main(String[] args) {
        BuyTicket buyTicket=new BuyTicket();

        new Thread(buyTicket,"JOJO").start();
        new Thread(buyTicket,"RAY").start();
        new Thread(buyTicket,"FISH").start();
    }
}

class BuyTicket implements Runnable{

    private int TicketNums=100;
    boolean flag=true;

    @Override
    public void run() {
        while (flag){
            Buy();

            //模拟延时  放大问题发生性
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private synchronized void Buy()  {
        if(TicketNums<=0){
            flag=false;
            return;//停止，退出run方法
        }

        System.out.println(Thread.currentThread().getName()+"拿到了第"+TicketNums--+"张票");
    }
}