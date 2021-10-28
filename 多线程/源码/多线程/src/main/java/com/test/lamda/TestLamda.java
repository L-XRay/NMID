package com.test.lamda;

public class TestLamda {
    public static void main(String[] args) {
        Love love=((p1, p2) -> {System.out.println(p1+":I love you"); System.out.println(p2+":I love you too");});
        //数据类型要么全省要么都不省
        love.Person("JOJO","Ray");
    }

}

interface Love{
    void Person(String p1,String p2);
}
