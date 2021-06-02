# 第六章笔记 static、final和常量设计

[TOC]

## 第一节 static

- static 静态的，Java中特殊的关键字

  —变量      —方法      —类        —匿名方法块

------

### 静态变量，类共有成员

1. static变量只依赖于类存在(通过类访问)
2. **<u>所有的对象实例关于静态变量的值都共享存储在一个共同的空间(栈)</u>**

### 静态方法

1. static方法通过类名直接引用
2. 静态方法中只能使用静态变量
3. <u>非静态方法可以调用静态方法，而静态方法不能调用非静态方法</u>

### static块

1. 只在类第一次被加载时调用
2. 在程序运行过程中，这段代码只运行一次
3. **<u>执行顺序：static块>匿名块>构造函数</u>**

------

## 第二节 单例模式

- 单例模式，又叫单态模式(Singleton)
- 限定某一个类在整个程序运行过程中，只保留一个实例对象在内存空间，即该类只能new一次

------

- 单例模式：保证一个类有且只有一个对象

  —采用static共享对象实例

  —采用private构造函数，防止外界new操作

------

## 第三节 final

- final修饰

  —类 

  —方法

  —字段

- final类不能被继承

- 父类中如果有final的方法，子类中不能改写该方法

- final变量不能再次赋值

  1. 基本变量不能修改值
  2. 对象实例不能修改指针，指向一个新的对象(内存空间)，但能修改对象内部

------

## 第四节 常量设计和常量池

### 常量设计

- 常量：值保持不变的量

  —Java中没有const关键字

  —不能修改，final

  —不会修改/只读/只要一份，static

  —方便访问public

- Java中的常量

  —public static final

  —常量名全大写

------

特殊的常量：***<u>接口内定义的变量默认都是常量</u>***

------

### 常量池

- 常量池：相同的值只存储一份，节省内存，共享访问
- 基本类型的包装类

|        包装类        |      常量池      |
| :------------------: | :--------------: |
|       Boolean        |   true，false    |
|         Byte         |     -128~127     |
| Short，Integer，Long |     -128~127     |
|      Character       |      0~127       |
|    Float，Double     | 没有缓存(常量池) |

------

![](%E7%AC%AC%E5%85%AD%E7%AB%A0%E7%AC%94%E8%AE%B0%20static%E3%80%81final%E5%92%8C%E5%B8%B8%E9%87%8F%E8%AE%BE%E8%AE%A1.assets/%E7%AC%94%E8%AE%B01.JPG)

------

#### 基本类型的包装类和字符串的两种创建方式

1. **常量式(字面量)赋值创建，放在<u>栈内存(将被常量化)</u>**
   - Integer a=10;
   - String b=“abc”;
2. **new对象进行创建，放在<u>堆内存(不会常量化)</u>**
   - Integer c=new Integer(10);
   - String d=new String("abc");

Tips：栈内存读取速度快但容量小；堆内存读取速度慢但容量大

------

##### Integer的两种创建方式

```java
public class BoxClassTest {
	public static void main(String[] args)
	{
		int i1 = 10;
		Integer i2 = 10;                //自动装箱
		System.out.println(i1 == i2);   //true
		// 自动拆箱 基本类型和包装类进行比较，包装类自动拆箱
		
		Integer i3 = new Integer(10);
		System.out.println(i1 == i3);  //true
		// 自动拆箱 基本类型和包装类进行比较，包装类自动拆箱
		
		System.out.println(i2 == i3); //false
		// 两个对象比较，比较其地址 
		//i2是常量，放在栈内存，i3是new出对象，放在堆内存
        
		Integer i4 = new Integer(5);
		Integer i5 = new Integer(5);
		System.out.println(i1 == (i4+i5));   //true
		System.out.println(i2 == (i4+i5));   //true
		System.out.println(i3 == (i4+i5));   //true
		// i4+i5 使i4,i5自动拆箱为基本类型并运算得到10
		// 基本类型10和对象比较，将会使对象自动拆箱，做基本类型比较
		
		Integer i6 = i4 + i5;  //+操作使i4,i5自动拆箱，得到10，因此i6==i2
		System.out.println(i1 == i6);  //true
		System.out.println(i2 == i6);  //true
		System.out.println(i3 == i6);  //false
        //i3是new出对象，放在堆内存，而i6是常量，放在栈内存，指向地址不同
	}	
}
```

##### String的两种创建方式

```java
public class StringNewTest {
	public static void main(String[] args) {
		String s0 = "abcdef";
		String s1 = "abc";
		String s2 = "abc";
		String s3 = new String("abc");
		String s4 = new String("abc");
		System.out.println(s1 == s2); //true 在同一常量池，指向同一地址
		System.out.println(s1 == s3); //false s1栈内存 s2堆内存
		System.out.println(s3 == s4); //false s3,s4都在堆内存，但指向地址不同
		System.out.println("=========================");
		
		String s5 = s1 + "def";    //涉及变量，编译器不优化
		String s6 = "abc" + "def"; //都是常量，编译器会自动优化为abcdef
		String s7 = "abc" + new String ("def");//涉及到new对象，编译器不优化
		System.out.println(s5 == s6); //false
		System.out.println(s5 == s7); //false
		System.out.println(s6 == s7); //false
		System.out.println(s0 == s6); //true 
		System.out.println("=========================");

		
		String s8 = s3 + "def";//涉及new对象，编译器不优化
		String s9 = s4 + "def";//涉及new对象，编译器不优化
		String s10 = s3 + new String("def");//涉及new对象，编译器不优化
		System.out.println(s8 == s9); //false
		System.out.println(s8 == s10); //false
		System.out.println(s9 == s10); //false
        //s8,s9,s10都在堆内存，值相同但都分开存储，指向地址也不同
	}
}
```

------

## 第五节 不可变对象和字符串

### 不可变对象

- 不可变对象(Immutable Object)

  —一旦创建，这个对象(状态/值)不能被更改

  —其内在的成员变量的值不能修改

  —基本类型的包装类

  —String,BigInteger和BigDecimal等

- 可变对象(Mutable Object)

  —普通对象

------

- 不可变对象也是传递指针
- 由于不可变，临时变量指向新内存，外部实参的指针不改动

------

#### 创建不可变对象

- Immutable对象是不可改变的，若需改变，用clone/new一个对象进行修改
- 所有的属性都是final和private
- 不提供setter方法
- 类是final的，或者所有方法都是final
- 若类中包含mutable对象，那么返回拷贝需要深度clone(cloneable接口)

------

不可变对象优点

1. 只读，线程安全
2. 并发读，提高性能
3. 重复使用

不可变对象缺点

1. 浪费空间

   —对一个不可变对象进行修改时，会新开辟空间，旧对象则被搁置，直到垃圾回收

------

### Java字符串

- 字符串是Java使用最多的类，是一种典型的不可变对象

- String定义的方法

  ```java
  String a = "abc";  //常量赋值，栈分配内存
  String b = new String("abc"); //new对象，堆分配内存
  ```

- 字符串内容比较：equals方法
- 是否指向同一对象：指针比较 ==

------

#### 字符串的加法

```java
String a="abc";
a=a+"def";  
//由于String不可修改，会申请一个新的空间，旧空间依旧存在，指针指向新的空间，效率差
```

- Java中使用StringBuffer/StringBuilder类的append方法进行修改
- StringBuffer/StringBulider的对象都是可变对象

##### StringBuffer类

- 同步
- 线程安全
- 修改快速

##### StringBulider

- 不同步
- 线程不安全
- 修改更快





