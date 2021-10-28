package com.test.StaticProxy;

//好处
//代理角色可以增加真实角色的功能：如记录日志
//真实角色专注自己的功能
public class StaticProxy {
    public static void main(String[] args) {

        Person person=new Person();
        WeddingCompany company=new WeddingCompany(person);
        company.HappyMarry();

        //new WeddingCompany(new Person()).HappyMarry();
    }
}

interface Marry{
    void HappyMarry();
}

//真实角色
class Person implements Marry{
    public void HappyMarry() {
        System.out.println("我要结婚了");
    }
}

//代理角色
class WeddingCompany implements Marry{
    //代理谁--->真是目标角色
    private Marry target;

    public WeddingCompany(Marry target) {
        this.target = target;
    }

    public void HappyMarry() {
        before();
        this.target.HappyMarry();
        after();
    }

    private void after() {
        System.out.println("结婚后");
    }

    private void before() {
        System.out.println("结婚前");
    }

}


