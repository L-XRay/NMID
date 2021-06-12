package cn.com;

public class USD extends Currency implements Comparable<Currency> {
    // 实现你的构造函数与Comparable中的接口
    public USD(int u){
        this.setName(CURRENCY_NAME[2]);
        this.setOriginalValue(u);
        int i= CURRENCY_RATIO[0];
        int j= CURRENCY_RATIO[2];
        float k=(float)i/j;
        u=(int)(u*k);
        this.setValue(u);
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
        if(this.getValue()< another.getValue()){
            return -1;
        }
        else{
            return 1;
        }
   }
}
