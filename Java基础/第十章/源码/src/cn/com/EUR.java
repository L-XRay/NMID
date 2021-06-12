package cn.com;

public class EUR extends Currency implements Comparable<Currency> {
    // 实现你的构造函数与Comparable中的接口
    public EUR(int e){
        this.setName(CURRENCY_NAME[3]);
        this.setOriginalValue(e);
        int i= CURRENCY_RATIO[0];
        int j= CURRENCY_RATIO[3];
        float k=(float)i/j;
        e=(int)(e*k);
        this.setValue(e);
    }

//    public int compareTo(Currency another) {
//        int i= getName().compareTo(another.getName());
//        if (i == 0) {
//            return this.getValue()- another.getValue();
//        } else {
//            return i;
//        }
//    }
    public int compareTo(Currency another) {
        if (this.getValue() < another.getValue()) {
            return -1;
        } else {
            return 1;
        }
    }
}
