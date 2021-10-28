package com.test.PtoCProblem;

import com.sun.org.apache.bcel.internal.generic.NEW;

//生产者消费者问题——>标志位
//信号灯法
public class Signer {
    public static void main(String[] args) {
        Bv bv=new Bv();
        new UP(bv).start();
        new User(bv).start();
    }
}

//生产者——>UP主
class UP extends Thread{
    Bv bv;

    public UP(Bv bv) {
        this.bv = bv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100;i++) {
            if(i%2==0){
                this.bv.Upload("JOJO");
            }
            else {
                this.bv.Upload("Ray");
            }
        }
    }
}

//消费者——>用户
class User extends Thread{
    Bv bv;

    public User(Bv bv) {
        this.bv = bv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            this.bv.view();
        }
    }
}

//产品——>视频
class Bv{
    //UP主上传，用户等待 T
    //用户观看，UP主等待 F
    String name;
    boolean flag=true;

    //上传
    public synchronized void Upload(String name){
        if(!flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("UP主上传"+name);

        //通知用户观看
        this.notifyAll();

        this.name=name;
        this.flag=!this.flag;

    }

    //观看
    public synchronized void view(){
        if(flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("用户观看"+name);

        //通知UP主上传
        this.notifyAll();
        this.flag=!this.flag;

    }
}