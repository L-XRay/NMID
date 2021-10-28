package com.test.State;

public class TestState {
    public static void main(String[] arg) {
        Time time=new Time();
        Thread thread=new Thread(time);
        Thread.State state=thread.getState();
        System.out.println(state);// new

        thread.start();
        state=thread.getState();//
        System.out.println(state);//RUNNABLE

        while(state!=Thread.State.TERMINATED){ //线程未结束一直输出状态
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            state=thread.getState();
            System.out.println(state);
        }
    }
}

class Time implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }
    }

}
