package com.coin.part.type;

import com.coin.concept.Component;
import com.coin.concept.Workable;

// CPU的抽象定义，继承自Component，实现Workable
public abstract class CPU extends Component implements Workable {

    protected int coreNum;

    public CPU(String name, int coreNum, double price) {
        super(name, price);
        this.coreNum = coreNum;
    }

    public String description() {
        StringBuilder cpuDescBuilder = new StringBuilder();
        cpuDescBuilder.append(super.description())
                .append(" Core Num:{")
                .append(coreNum)
                .append("}");
        return cpuDescBuilder.toString();
    }

}
