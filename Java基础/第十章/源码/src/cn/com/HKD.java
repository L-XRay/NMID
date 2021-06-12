package cn.com;

public class HKD extends Currency implements Comparable<Currency> {
    // 实现你的构造函数与Comparable中的接口
    public HKD(int h) {
        this.setName(CURRENCY_NAME[1]); //当前货币类型
        this.setOriginalValue(h);//赋初值
        int i = CURRENCY_RATIO[0];//人民币100
        int j = CURRENCY_RATIO[1];//对应人民币100的当前货币数目
        float k = (float) i / j;//汇率
        h = (int) (h * k);//换算成人民币后的当前货币数目
        this.setValue(h);//
    }

    //    public int compareTo(Currency another) {
//        int i = this.getName().compareTo(another.getName());
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
