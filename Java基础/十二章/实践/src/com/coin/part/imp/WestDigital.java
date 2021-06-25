package com.coin.part.imp;

import com.coin.part.type.Disk;

public class WestDigital extends Disk { //西部数据硬盘

    public WestDigital(String name, double price, int volume) {
        super(name, price, volume);
    }

    public void work() {
        System.out.println("This is westdigital disk working");
    }

}

