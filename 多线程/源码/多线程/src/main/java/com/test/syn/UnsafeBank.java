package com.test.syn;

public class UnsafeBank {
    public static void main(String[] args) {
        //这里new了两个user，锁的是两个不同的对象，单纯在方法上加syn锁不到两个对象，要用sync块
        User user=new User("奖学金",2000);
        Draw Owner=new Draw(user,500,"我");
        Draw Parents=new Draw(user,2000,"父母");

        Owner.start();
        Parents.start();
    }
}

class User{
    String name;
    int money;

    public User(String name, int money) {
        this.name = name;
        this.money = money;
    }
}

//模拟取钱
class Draw extends Thread{
    User user; //账户
    int DrawMoney; //取的钱数
    int HandMoney;//现在余额

    public Draw(User user,int DrawMoney,String name){
        super(name);   //=Thread(name)
        this.user=user;
        this.DrawMoney=DrawMoney;
    }

    //取钱
    @Override
    public  void run() {
        synchronized (user){
            //账户余额-取的钱数<=0，钱不够
            if(user.money-DrawMoney<=0){
                System.out.println(Thread.currentThread().getName()+"想取"+DrawMoney);
                System.out.println(user.name+"余额不足,无法取款!");
                return; //停止，退出run方法
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //现在的余额=账户余额-取出的钱数
            user.money=user.money-DrawMoney;
            //手上的钱=原来持有的钱+取出的钱
            HandMoney = HandMoney +DrawMoney;

            System.out.println(Thread.currentThread().getName()+"取出"+DrawMoney+"元成功！");
            System.out.println(user.name+"余额:"+user.money); //可能取钱的人不是银行卡持有者
            //Draw 继承 Thread，Thread.currentThread().getName()=this.getName();
            System.out.println(this.getName()+"持有:"+HandMoney);
        }
    }
}