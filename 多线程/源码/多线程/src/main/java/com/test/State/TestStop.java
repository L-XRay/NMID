package com.test.State;

//测试stop
//建议线程正常停止——利用次数循环
//使用标志位
public class TestStop implements Runnable{

    //设置一个标志位
    private boolean flag=true;

    @Override
    public void run() {
        int i=0;
        while(flag){
            System.out.println("Run:"+i++);  //循环结束？线程应该没结束吧？
        }
    }

    //设置公开的方法停止线程，转换标志位
    public void stop(){
        this.flag=false;
    }

    public static void main(String[] args) {
        TestStop testStop=new TestStop();

        new Thread(testStop).start();

        for(int i=1;i<=500;i++){
            System.out.println("main:"+i);
            if(i==250){
                //调用stop方法转换标志位。停止线程
                testStop.stop();
                System.out.println("线程结束!主线程继续执行到500");
            }
        }
    }
}
