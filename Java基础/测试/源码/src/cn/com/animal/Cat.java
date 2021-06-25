package cn.com.animal;

public class Cat extends Animal implements Comparable<Animal>{
    //实现你的构造函数与Comparable中的接口
    public Cat(String string, int age) {
        this.setName(string);
        mage = cat[age];
        this.setAge(age);//获取输入猫年龄
        this.setMage(mage);//获取输入猫年龄对应的人的年龄
    }

    public int compareTo(Animal another) {
        return mage-another.mage;
    }

}