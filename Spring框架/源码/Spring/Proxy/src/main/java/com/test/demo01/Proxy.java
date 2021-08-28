package com.test.demo01;

import static java.lang.Thread.sleep;

public class Proxy implements Renter{
    private Landlord landlord; //组合

    public Proxy(){
    }
    public Proxy(Landlord landlord){
        this.landlord=landlord;
    }

    @Override
    public void rent() {
        landlord.rent();
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("………………");
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        seehouse();
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("………………");
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        hetong();
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("………………");
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cost();
    }
    //合成复用原则

    public void seehouse(){
        System.out.println("中介带你看房");
    }

    public void hetong(){
        System.out.println("签署中介合同");
    }

    public void cost(){
        System.out.println("交费完成");
    }
}
