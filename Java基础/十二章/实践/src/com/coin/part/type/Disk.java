package com.coin.part.type;

import com.coin.concept.Component;
import com.coin.concept.Workable;

 //磁盘的抽象定义，继承自Component，实现Workable
public abstract class Disk extends Component implements Workable {

    protected int volume;

    public Disk(String name, double price, int volume) {
        super(name, price);
        this.volume = volume;
    }

    public String description() {
        StringBuilder cpuDescBuilder = new StringBuilder();
        cpuDescBuilder.append(super.description())
                .append(" size(MB):{")
                .append(volume)
                .append(" MB}");
        return cpuDescBuilder.toString();
    }

}
