package cn.com.animal;

public class Animal {
    int age;//动物年龄

    int mage; //映射到人的年龄

    String name; //名字
    static int[] dog = {0,18,23,28,32,36,40,45,50,55,60};
    static int[] cat= {0,15,24,28,32,36,40,44,48,52,56};

    public void setAge(int age) {
        this.age = age;
    }

    public void setMage(int mage) {
        this.mage = mage;
    }

    public void setName(String name) {
        this.name = name;
    }

}
