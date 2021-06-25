package com.coin.part.imp;

import com.coin.part.type.Disk;

public class Seagate extends Disk { //希捷硬盘

    public Seagate(String name, double price, int volume) {
        super(name, price, volume);
    }

    public void work() {
        System.out.println("This is seagate disk working");
    }

}
