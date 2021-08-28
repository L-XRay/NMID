package com.test.demo03;

//真实角色 房东
public class Landlord implements Renter {

    @Override
    public void rent() {
        System.out.println("房东出租房子");
    }
}
