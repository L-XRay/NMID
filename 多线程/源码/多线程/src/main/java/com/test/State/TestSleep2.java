package com.test.State;

import java.text.SimpleDateFormat;
import java.util.Date;

//模拟倒计时
public class TestSleep2 {

    public static void main(String[] args) throws InterruptedException {
        //模拟倒计时
        time();

        System.out.println("开始计时!");
        //获取系统当前时间
        Date currenTime=new Date();

        while(true){
            System.out.println(new SimpleDateFormat("HH:mm:ss").format(currenTime));
            Thread.sleep(1000);
            currenTime=new Date();
        }
    }

    public static void time() throws InterruptedException {
        int num=10;

        while(true){
            if(num<0){
                break;
            }
            else{
                System.out.println(num--);
            }
            Thread.sleep(1000);
        }
    }
}
