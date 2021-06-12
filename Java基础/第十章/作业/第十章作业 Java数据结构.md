### 第十章作业 Java数据结构

#### Main

```java
package cn.com;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Currency[] cs = new Currency[3];
        //初始化
        System.out.println("请按照顺序输入港币HKD，美元USD和欧元EUR");
        Scanner sc = new Scanner(System.in);
        //利用hasNextXXX()判断是否还有下一输入项
        int a = 0;
        int b = 0;
        int c = 0;

        if (sc.hasNext()) {
            a = sc.nextInt();
            cs[0] = new HKD(a);
        }

        if (sc.hasNext()) {
            b = sc.nextInt();
            cs[1] = new USD(b);
        }

        if (sc.hasNext()) {
            c = sc.nextInt();
            cs[2] = new EUR(c);
        }
        //初始化结束
        Arrays.sort(cs);
        // 请补充排序
//        Currency n;
//        for (int i = 0; i < 2; i++) {
//            if (cs[i].value>cs[i+1].value) {
//                n=cs[i];
//                cs[i]=cs[i+1];
//                cs[i+1]=n;
//            }
//        }
        for (Currency m : cs)
        {
            System.out.println(m.getName()+":"+m.getOriginalValue());//请补充输出结果
        }
    }
}
```

#### Currency

```java
package cn.com;

public class Currency {
    private String name;        //货币名称
    private int originalValue;  //原始值
    public int value;          //转换为人民币后的值
    public static String[] CURRENCY_NAME = {"CNY", "HKD", "USD", "EUR"};
    public static int[] CURRENCY_RATIO = {100, 118, 15, 13};

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getOriginalValue() {
        return originalValue;
    }
    public void setOriginalValue(int originalValue) {
        this.originalValue = originalValue;
    }

    public int getValue() { return value; }
    public void setValue(int value) {
        this.value = value;
    }

    public Currency(String name, int originalValue, int value) {
        this.name = name;
        this.originalValue = originalValue;
        this.value = value;
    }

    public Currency() {
        super();
    }

}
```

#### HKD

```java
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
```

#### USD

```java
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
```

#### EUR

```java
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
```

**运行结果：**

![](%E7%AC%AC%E5%8D%81%E7%AB%A0%E4%BD%9C%E4%B8%9A%20Java%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84.assets/%E8%BF%90%E8%A1%8C%E7%BB%93%E6%9E%9C.JPG)