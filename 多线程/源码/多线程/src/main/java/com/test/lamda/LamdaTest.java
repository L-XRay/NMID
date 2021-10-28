package com.test.lamda;

//推导lamda表达式
public class LamdaTest {

    //3.静态内部类
    static class OU2 implements IOU {
        @Override
        public void lamda() {
            System.out.println("静态内部类:lamda is nice");
        }
    }

    public static void main(String[] args) {
        IOU ou=new OU1();
        ou.lamda();

        ou=new OU2();
        ou.lamda();

        //4.局部内部类
        class OU3 implements IOU{
            @Override
            public void lamda() {
                System.out.println("局部内部类:lamda is nice");
            }
        }
        ou=new OU3();
        ou.lamda();

        //5.匿名内部类:没有类的名称，必须借助接口或者父类
        ou=new IOU() {
            @Override
            public void lamda() {
                System.out.println("匿名内部类:lamda is nice");
            }
        };
        ou.lamda();

        //6.用lamda简化
        ou=()-> {
            System.out.println("lamda:lamda is nice");
        };
        ou.lamda();

    }
}

//1.定义一个函数式接口
interface IOU{
    void lamda();
}

//2.实现类
class OU1 implements IOU{
    @Override
    public void lamda() {
        System.out.println("实现类:lamda is nice");
    }
}