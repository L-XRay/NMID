package com.coin.part.imp;

import com.coin.part.type.CPU;


public class AMDCPU extends CPU {

    public AMDCPU(String name, int coreNum, double price) {
        super(name, coreNum, price);
    }

    public void work() {
        System.out.println("This is AMD's cpu working");
    }

}
