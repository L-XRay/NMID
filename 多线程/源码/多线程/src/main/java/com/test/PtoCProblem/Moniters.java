package com.test.PtoCProblem;

//生产者消费者问题——>利用缓冲区解决
//管程法

//生产者  消费者  产品  缓冲区
public class Moniters {
    public static void main(String[] args) {
        SynContainer container=new SynContainer();

        new Productor(container).start();
        new Consumer(container).start();
    }
}

//生产者
class Productor extends Thread{
    SynContainer container;

    public Productor(SynContainer container) {
        this.container = container;
    }

    //生产
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("生产了"+i+"号牛肉");
            container.setBeefs(new Beef(i));  //把当前牛肉放入容器
        }
    }
}

//消费者
class Consumer extends Thread{
    SynContainer container;

    public Consumer(SynContainer container) {
        this.container = container;
    }

    //消费
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("消费了"+container.getBeefs().id+"号牛肉");
        }
    }
}

//产品
class Beef {
    int id; //产品编号

    public  Beef(int id) {
        this.id = id;
    }
}

//缓冲区
class SynContainer {
    //需要一个容器大小
    Beef[] beefs=new Beef[10];

    //容器计数器
    int count=0;

    //生产者放入产品
    public synchronized void setBeefs(Beef beef){
        //容器满了，等待消费者消费
        while(count==beefs.length){  //if语句中醒来的线程 不会再一次进行判断了 而while会重新再判断
            //通知消费者消费，生产者等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //没满，需要放入产品
        beefs[count]=beef;
        count++; //容器里的产品+1

        //通知消费者取出产品
        this.notifyAll();
    }

    //消费者取走产品
    public synchronized Beef getBeefs(){
        //判断能否取走
        while(count==0){
            //等待生产者生产，消费者等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //能取走
        count--;  //因为生产者生产完会+1，最后的数组下标会多1，所以这里要拿到应该-1
        Beef beef=beefs[count];
        //通知生产者生产
        this.notifyAll();

        return beef;  //返回取走的产品
    }

}

