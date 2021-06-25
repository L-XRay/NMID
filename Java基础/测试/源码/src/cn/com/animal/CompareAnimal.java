package cn.com.animal;

import java.util.Arrays;
import java.util.Scanner;

public class CompareAnimal {
    public static void main(String[] args) {
        System.out.println("请依次输入四个动物年龄(1~10):");
        Animal[] as = new Animal[4];
        // 初始化
        Scanner sc = new Scanner(System.in);
        // 利用hasNextXXX()判断是否还有下一输入项
        System.out.print("dog1:");
        if (sc.hasNext()) {
            as[0] = new Dog("dog1", sc.nextInt());
        }
        System.out.print("cat1:");
        if (sc.hasNext()) {
            as[1] = new Cat("cat1", sc.nextInt());
        }
        System.out.print("dog2:");
        if (sc.hasNext()) {
            as[2] = new Dog("dog2", sc.nextInt());
        }
        System.out.print("cat2:");
        if (sc.hasNext()) {
            as[3] = new Cat("cat2", sc.nextInt());
        }

        // 请补充排序
        System.out.println("四个动物年龄升序排序为:");
        Arrays.sort(as);
        for (Animal animal : as) {
            System.out.println(animal.name+","+animal.age);
        }
        // 请补充升序输出结果
    }

}
