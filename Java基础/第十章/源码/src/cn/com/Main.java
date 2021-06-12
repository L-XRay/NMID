package cn.com;

import java.util.Arrays;
import java.util.Scanner;1

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

