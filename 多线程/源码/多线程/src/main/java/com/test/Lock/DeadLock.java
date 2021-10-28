package com.test.Lock;

//多个线程互相拿着对方需要的资源，造成僵持的局面
public class DeadLock {
    public static void main(String[] args) {
        Eat eat=new Eat(0,"JOJO");
        Eat eat1=new Eat(1,"Ray");

        eat.start();
        eat1.start();
    }
}

//水果
class Fruit{

}

//蔬菜
class Vegetable{

}

class Eat extends Thread{
    //static保证需要的资源只有一份
    static Fruit fruit=new Fruit();
    static Vegetable vegetable=new Vegetable();

    int choice; //选择
    String name; //吃货

    public Eat(int choice,String name){
        this.choice=choice;
        this.name=name;
    }

    @Override
    public void run() {
        try {
            eat();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //互相拿着对方想吃的食物
    private void eat() throws InterruptedException {
        if(choice==0){
            synchronized (fruit){
                System.out.println(this.name+"获得水果的锁");
                Thread.sleep(1000);
//                //一秒钟后想吃蔬菜
//                synchronized (vegetable){
//                    System.out.println(this.name+"获得蔬菜的锁");
//                }
            }
            //先把水果的锁释放，再去拿现在需要的蔬菜，不能既拿着水果的锁，又拿着蔬菜的锁，鱼和熊掌不可兼得
            synchronized (vegetable){
                System.out.println(this.name+"获得蔬菜的锁");
            }
        }
        else{
            synchronized (vegetable){
                System.out.println(this.name+"获得蔬菜的锁");
                Thread.sleep(2000);
//                //两秒钟后想吃水果
//                synchronized (fruit){
//                    System.out.println(this.name+"获得水果的锁");
                }
                //先把蔬菜的锁释放，再去拿现在需要的水果，不能既拿着水果的锁，又拿着蔬菜的锁，鱼和熊掌不可兼得
                synchronized (fruit){
                System.out.println(this.name+"获得水果的锁");
            }
        }
    }
}

