# 第十章笔记 Java数据结构

[TOC]

## 第一节 数组

- 数组是一个存放多个数据的容器
  - 数据是同一种类型
  - 所有的数据是线性规则排列
  - 可通过位置索引来快速定位访问数据
  - 需明确容器的长度

### Java数组定义和初始化

```java
int a[]; // a 还没有new操作，实际上是null，也不知道内存位置
int[] b; // b 还没有new操作，实际上是null，也不知道内存位置
int[] c=new int[2]; // c 有2个元素，都是0
c[0]=10;c[1]=20; // 逐个初始化

int d[]=new int[]{0,2,4}; // d 有3个元素，0,2,4，同时定义和初始化
int d1[]={1,3,5}; // d1 有3个元素，1,3,5，同时定义和初始化
//声明变量时没有分配内存，不指定大小
```

------

### 数组索引

- 数组的length属性标识数组的长度
- 从0开始，到length-1
- int[] a = new int[5];   //  a[0]~a[4]    a.length=5
- 数组不能越界访问(ArrayIndexOutOfBoundsException异常)

### 数组遍历

```java
//需要自己控制索引位置
for(int i=0;i<d.length;i++)
{
    System.out.println(d[i]);
}

//for-each 语句 无需控制索引位置，不存在也不会出现越界访问
for(int e : d)
{
    System.out.println(e);
}
```

### 多维数组

- 数组的数组
- 按照行存储原则存储

```java
//规则数组
int a[][]=new int[2][3];

//不规则数组
int b[][];
b=new int[3][];
b[0]=new int[3];
b[1]=new int[4];
b[2]=new int[5];

//两种遍历
int k=0;
for(int i=0;i<a.length;i++)
{
    for(int j=0;j<a[i].length;j++)
    {
        a[i][j]=++k;
    }
}

for(int[] item : a)
{
    for(int item : items)
    {
        System.out.print(item+",");
    }
    System.out.println();
}
```

------

## 第二节 JCF

- 容器：能够存放数据的空间结构
  - 数组/多维数组，只能线性存放
  - 列表/散列集/树/……
- 容器框架：为表示和操作容器而规定的一种标准体系结构
  - 对外的接口：容器中所能存放的抽象数据类型
  - 接口的实现：可复用的数据结构
  - 算法：对数据的查找和排序
- 容器框架优点：提高数据存取效率

![](%E7%AC%AC%E5%8D%81%E7%AB%A0%E7%AC%94%E8%AE%B0%20Java%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84.assets/JCF%E7%BB%93%E6%9E%84%E5%9B%BE.JPG)

------

### JCF主要的数据结构实现类

- 列表(List,ArrayList,LinkedList)
- 集合(Set,HashSet,TreeSet,LinkedHashSet)
- 映射(Map,HashMap,TreeMap,LinkedHashMap)

### JCF主要的算法类

- Arrays：对数组进行查找和排序等操作
- Collections：对Collection及其子类进行排序和查找操作

------

### JCF的集合接口：Collection

- add 添加
- contains 包含
- remove 删除
- size 数据元素个数

### JCF的迭代器接口：Iterator

- hasNext 判断是否有下一个元素
- next 获取下一个元素
- remove 删除某一个元素

------

## 第三节 列表List

- List：列表
  - 有序的Collection
  - 允许重复元素
  - {1,2,4,{5,2},1,3}
- List主要实现
  - ArrayList(非同步)
  - LinkedList(非同步)
  - Vector(同步)

------

### ArrayList

- 以数组实现的列表，不支持同步
- 利用索引位置可以快速定位访问
- 不适合指定位置的插入、删除操作
- 适合变动不大、主要用于查询的数据
- 容量可动态调整
- ArrayList在元素填满容器时会自动扩充容器大小的50%

```java
import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListTest {
   public static void main(String[] a) {  
       ArrayList<Integer> al = new ArrayList<Integer>(); 
       // 泛型表示，意思时al这个数据结构中只能容纳Integer的对象
       al.add(3);  // add(3)自动将普通int变量自动装箱为Integer(3)的对象，然后放入ArrayList容器中
       al.add(2);          
       al.add(1);  
       al.add(4);  
       al.add(5);  
       al.add(6); 
       al.add(new Integer(6));  
     
       System.out.print("The third element is  ");
       System.out.println(al.get(3));
       al.remove(3);  //删除第四个元素，后面元素往前挪动
       al.add(3, 9);  //将9插入到第4个元素，后面元素往后挪动
       
       System.out.println("======遍历方法============");
       
       ArrayList<Integer> as = new ArrayList<Integer>(100000);
       for (int i=0; i<100000; i++)
       {
          as.add(i);
       }
       traverseByIterator(as);
       traverseByIndex(as);
       traverseByFor(as);    
   }  
   public static void traverseByIterator(ArrayList<Integer> al)
   {
      long startTime = System.nanoTime();
      System.out.println("============迭代器遍历=============="); 
       Iterator<Integer> iter1 = al.iterator();  
       while(iter1.hasNext()){  
           iter1.next();  
       }
      long endTime = System.nanoTime();
       long duration = endTime - startTime;
       System.out.println(duration + "纳秒");
   }
   public static void traverseByIndex(ArrayList<Integer> al)
   {
      long startTime = System.nanoTime();
      System.out.println("============随机索引值遍历=============="); 
       for(int i=0;i<al.size();i++)
       {
          al.get(i);
       }
      long endTime = System.nanoTime();
       long duration = endTime - startTime;
       System.out.println(duration + "纳秒");
   }
   public static void traverseByFor(ArrayList<Integer> al)
   {
      long startTime = System.nanoTime();
      System.out.println("============for-each语句遍历=============="); 
       for(Integer item : al)
       {
          ;
       }
      long endTime = System.nanoTime();
      long duration = endTime - startTime;
      System.out.println(duration + "纳秒");
   }
}
```

### LinkedList

- 以双向链表实现的列表，不支持同步
- 可当作堆栈、队列和双端队列进行操作
- 顺序访问高效，随机访问较差，中间插入和删除高效
- 适用于经常变化的数据

```java
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListTest {

   public static void main(String[] args) {
      LinkedList<Integer> ll = new LinkedList<Integer>();  
       ll.add(3);  
       ll.add(2);  
       ll.add(5);  
       ll.add(6);  
       ll.add(6);  
       System.out.println(ll.size()); //5
       ll.addFirst(9);  //在头部增加元素9
       ll.add(3, 10);   //将10插入到第四个元素，四及后面的元素往后挪动
       ll.remove(3);    //将第四个元素删除
       
       LinkedList<Integer> list = new LinkedList<Integer>();
       for (int i=0; i<100000; i++)
       {
          list.add(i);
       }
       traverseByIterator(list);
       traverseByIndex(list);
       traverseByFor(list);    

   }
   
  
   public static void traverseByFor(LinkedList<Integer> list)
   {
      long startTime = System.nanoTime();
      System.out.println("============for-each语句遍历=============="); 
       for(Integer item : list)
       {
          ;
       }
      long endTime = System.nanoTime();
       long duration = endTime - startTime;
       System.out.println(duration + "纳秒");
   }
}
```

### Vector

- 和ArrayList类似，可变数组实现的列表
- Vector同步，适合在多线程下使用

```java
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;
+2.
public class VectorTest {

   public static void main(String[] args) {
      Vector<Integer> v = new Vector<Integer>();
      v.add(1);
      v.add(2);
      v.add(3);
      v.remove(2);
      v.add(1, 5);
      System.out.println(v.size());

   public static void traverseByFor(Vector<Integer> v) {
      long startTime = System.nanoTime();
      System.out.println("============for-each语句遍历==============");
      for (Integer item : v) {
         ;
      }
      long endTime = System.nanoTime();
      long duration = endTime - startTime;
      System.out.println(duration + "纳秒");
   }

   public static void traverseByEnumeration(Vector<Integer> v) {
      long startTime = System.nanoTime();
      System.out.println("============Enumeration遍历==============");
      for (Enumeration<Integer> enu = v.elements(); enu.hasMoreElements();) {
         enu.nextElement();
      }
      long endTime = System.nanoTime();
      long duration = endTime - startTime;
      System.out.println(duration + "纳秒");
   }
}
```

#### <u>***ArrayList适用于较多查询的(静态)数据***</u>

#### **<u>LinkedList适用于频繁增删的数据</u>**

------

## 第四节 集合Set

- 集合Set
  - 确定性：对任意对象都能判定其是否属于某一个集合
  - 互异性：集合内每个元素内容各不相同
  - 无序性：集合内的顺序无关
- Java中的集合接口Set
  - HashSet(基于散列函数的集合，无序，不同步)
  - TreeSet(基于树结构的集合，可排序，不同步)
  - LinkedHashSet(基于散列函数和双向链表的集合，可排序，不同步)

------

### HashSet

- 基于HashMap实现的，可以容纳null元素，不支持同步

- ```java
  Set s = Collections.synchronizedSet(new HashSet(……)) ;  //同步
  ```

- add 添加一个元素

- clear 清除整个HashSet

- contains 判定是否包含一个元素

- remove 删除一个元素

- size 大小

- retainAll 计算两个集合交集

```java
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class HashSetTest {
   public static void main(String[] args) {
      HashSet<Integer> hs = new HashSet<Integer>();
      hs.add(null);
      hs.add(1000);
      hs.add(20);
      hs.add(3);
      hs.add(40000);
      hs.add(5000000);
      hs.add(3);                      //3 重复 相当于无效代码
      hs.add(null);                   //null重复 相当于无效代码
      System.out.println(hs.size());  //6
      if(!hs.contains(6))
      {
         hs.add(6);
      }
      System.out.println(hs.size());  //7
      hs.remove(4);
      System.out.println(hs.size());  //6
      //hs.clear(); 清空内容
      //System.out.println(hs.size());  //0
      
      System.out.println("============for-each语句遍历=============="); 
       for(Integer item : hs)
       {
          System.out.println(item);
       }
       
       System.out.println("============测试集合交集==============");
       
       HashSet<String> set1 = new HashSet<String>();
       HashSet<String> set2 = new HashSet<String>();

        set1.add("a");
        set1.add("b");
        set1.add("c");

        set2.add("c");
        set2.add("d");
        set2.add("e");

        //交集
        set1.retainAll(set2);
        System.out.println("交集是"+set1);
        
        System.out.println("============���Զ��ֱ��������ٶ�==============");
      
      HashSet<Integer> hs2 = new HashSet<Integer>();
      for(int i=0;i<100000;i++)  {
         hs2.add(i);
      }
      traverseByIterator(hs2);
      traverseByFor(hs2);       
   }
   
   public static void traverseByIterator(HashSet<Integer> hs)
   {
      long startTime = System.nanoTime();
      System.out.println("============迭代器遍历=============="); 
       Iterator<Integer> iter1 = hs.iterator();  
       while(iter1.hasNext()){  
           iter1.next();  
       }
      long endTime = System.nanoTime();
       long duration = endTime - startTime;
       System.out.println(duration + "纳秒");
   }
   public static void traverseByFor(HashSet<Integer> hs)
   {
      long startTime = System.nanoTime();
      System.out.println("============for-each语句遍历=============="); 
       for(Integer item : hs)
       {
          ;
       }
      long endTime = System.nanoTime();
       long duration = endTime - startTime;
       System.out.println(duration + "纳秒");
   }
}
```

### LinkedHashSet

- 继承HashSet，也是基于HashMap实现的，可以容纳null元素，不支持同步

- ```java
  Set s = Collections.synchronizedSet(new HashSet(……)) ; //同步
  ```

- 方法和HashSet基本一致

- 通过一个双向链表维护插入顺序

```java
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class LinkedHashSetTest {
   public static void main(String[] args) {
      LinkedHashSet<Integer> lhs = new LinkedHashSet<Integer>();
      lhs.add(null);
      lhs.add(1000);
      lhs.add(20);
      lhs.add(3);
      lhs.add(40000);
      lhs.add(5000000);
      lhs.add(3);                      //3 重复
      lhs.add(null);                   //null 重复
      System.out.println(lhs.size());  //6
      if(!lhs.contains(6))
      {
         lhs.add(6);
      }
      System.out.println(lhs.size());  //7
      lhs.remove(4);
      System.out.println(lhs.size());  //6
      //lhs.clear();
      //System.out.println(lhs.size());  //0
      
      System.out.println("============for-each语句遍历=============="); 
       for(Integer item : lhs)
       {
          System.out.println(item);
       }
      
      LinkedHashSet<Integer> lhs2 = new LinkedHashSet<Integer>();
      for(int i=0;i<100000;i++)
      {
         lhs2.add(i);
      }
      traverseByIterator(lhs2);
      traverseByFor(lhs2);
      
   }
   
   public static void traverseByIterator(LinkedHashSet<Integer> hs)
   {
      long startTime = System.nanoTime();
      System.out.println("============迭代器遍历=============="); 
       Iterator<Integer> iter1 = hs.iterator();  
       while(iter1.hasNext()){  
           iter1.next();  
       }
      long endTime = System.nanoTime();
       long duration = endTime - startTime;
       System.out.println(duration + "纳秒");
   }
   public static void traverseByFor(LinkedHashSet<Integer> hs)
   {
      long startTime = System.nanoTime();
      System.out.println("============for-each语句遍历=============="); 
       for(Integer item : hs)
       {
          ;
       }
      long endTime = System.nanoTime();
       long duration = endTime - startTime;
       System.out.println(duration + "纳秒");
   }
}
```

------

### TreeSet

- 基于TreeMap实现，不可以容纳null元素，不支持同步

- ```java
  SortedSet s = Collections.synchronizedSortedSet(new TreeSet(……))； // 同步
  ```

- add 添加一个元素

- clear 清除整个TreeSet

- contains 判定是否包含一个元素

- remove 删除一个元素

- size 大小

- 根据compareTo方法或指定Comparator排序

```java
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class TreeSetTest {
   public static void main(String[] args) {
      TreeSet<Integer> ts = new TreeSet<Integer>();
      // ts.add(null); 错误，不支持null
      ts.add(1000);
      ts.add(20);
      ts.add(3);
      ts.add(40000);
      ts.add(5000000);
      ts.add(3);                      //3 重复
      System.out.println(ts.size());  //5
      if(!ts.contains(6))
      {
         ts.add(6);
      }
      System.out.println(ts.size());  //6
      ts.remove(4);
      System.out.println(ts.size());  //5
      //lhs.clear();
      //System.out.println(lhs.size());  //0
      
      System.out.println("============for-each语句遍历=============="); 
       for(Integer item : ts)
       {
          System.out.println(item);
       }
       
      TreeSet<Integer> ts2 = new TreeSet<Integer>();
      for(int i=0;i<100000;i++)
      {
         ts2.add(i);
      }
      traverseByIterator(ts2);
      traverseByFor(ts2);
      
   }
   
   public static void traverseByIterator(TreeSet<Integer> hs)
   {
      long startTime = System.nanoTime();
      System.out.println("============迭代器遍历=============="); 
       Iterator<Integer> iter1 = hs.iterator();  
       while(iter1.hasNext()){  
           iter1.next();  
       }
      long endTime = System.nanoTime();
       long duration = endTime - startTime;
       System.out.println(duration + "纳秒");
   }
   public static void traverseByFor(TreeSet<Integer> hs)
   {
      long startTime = System.nanoTime();
      System.out.println("============for-each语句遍历=============="); 
       for(Integer item : hs)
       {
          ;
       }
      long endTime = System.nanoTime();
       long duration = endTime - startTime;
       System.out.println(duration + "纳秒");
   }

}
```

LinkedHashSet是保留顺序的，其遍历顺序和插入顺序一致

HashSet没有保留顺序，其遍历顺序无序

TreeSet按照所存储对象大小升序输出

- HashSet，LinkedHashSet，TreeSet的元素都只能是对象

- HashSet和LinkedHashSet判定元素重复的原则

  —判定两个元素的hashCode返回值是否相同，若不同，返回false

  —若两者hashCode相同，判定equals方法，若不同，返回false；否则返回true

  —hashCode和equals方法是所有类都有的

- TreeSet判定元素重复的原则

  —需要元素继承自Comparable接口

  —比较两个元素的compareTo方法

```java
class Dog {
    private int size;
 
    public Dog(int s) {
        size = s;
    }      
    public int getSize() {
      return size;
   }

   public boolean equals(Object obj2)   {
       System.out.println("Dog equals()~~~~~~~~~~~");
       if(0==size - ((Dog) obj2).getSize()) {
          return true;
       } else {
          return false;
       }
    }
    
    public int hashCode() {
       System.out.println("Dog hashCode()~~~~~~~~~~~");
       return size;
    }
    
    public String toString() {
       System.out.print("Dog toString()~~~~~~~~~~~");
        return size + "";
    }
}
```

```java
class Cat
{
   private int size;
   
   public Cat(int size)
   {
      this.size = size;
   }
}
```

```java
public class Tiger implements Comparable{
   private int size;
    
    public Tiger(int s) {
        size = s;    
    }    
    
    public int getSize() {
      return size;
   }
    
   public int compareTo(Object o) {
       System.out.println("Tiger compareTo()~~~~~~~~~~~");
        return size - ((Tiger) o).getSize();
    }
}
```

```java
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.TreeSet;


public class ObjectHashSetTest {

   public static void main(String[] args) {
      System.out.println("==========Cat HashSet ==============");
      HashSet<Cat> hs = new HashSet<Cat>();  
      hs.add(new Cat(2));  
      hs.add(new Cat(1));  
      hs.add(new Cat(3));  
      hs.add(new Cat(5));  
      hs.add(new Cat(4)); 
      hs.add(new Cat(4)); 
      System.out.println(hs.size());  //6
      
      System.out.println("========================");
      LinkedHashSet<Cat> lhs= new LinkedHashSet<Cat>();  
      lhs.add(new Cat(2));  
      lhs.add(new Cat(1));  
      lhs.add(new Cat(3));  
      lhs.add(new Cat(5));  
      lhs.add(new Cat(4));  
      lhs.add(new Cat(4));      
      System.out.println(lhs.size());  //6
      
      
      
      System.out.println("==========Dog HashSet ==============");
      HashSet<Dog> hs2 = new HashSet<Dog>();  
      hs2.add(new Dog(2));  
      hs2.add(new Dog(1));  
      hs2.add(new Dog(3));  
      hs2.add(new Dog(5));  
      hs2.add(new Dog(4)); 
      hs2.add(new Dog(4)); 
      System.out.println(hs2.size());  //5
      
      System.out.println("========================");
      LinkedHashSet<Dog> lhs2= new LinkedHashSet<Dog>();  
      lhs2.add(new Dog(2));  
      lhs2.add(new Dog(1));  
      lhs2.add(new Dog(3));  
      lhs2.add(new Dog(5));  
      lhs2.add(new Dog(4));  
      lhs2.add(new Dog(4));     
      System.out.println(lhs2.size());  //5
      

      System.out.println("==========Tiger HashSet ==============");     
      HashSet<Tiger> hs3 = new HashSet<Tiger>();  
      hs3.add(new Tiger(2));  
      hs3.add(new Tiger(1));  
      hs3.add(new Tiger(3));  
      hs3.add(new Tiger(5));  
      hs3.add(new Tiger(4)); 
      hs3.add(new Tiger(4)); 
      System.out.println(hs3.size());  //6
      
      System.out.println("========================");
      LinkedHashSet<Tiger> lhs3= new LinkedHashSet<Tiger>();  
      lhs3.add(new Tiger(2));  
      lhs3.add(new Tiger(1));  
      lhs3.add(new Tiger(3));  
      lhs3.add(new Tiger(5));  
      lhs3.add(new Tiger(4));  
      lhs3.add(new Tiger(4));       
      System.out.println(lhs3.size());  //6
   }
}
```

```java
import java.util.TreeSet;


public class ObjectTreeSetTest {

   public static void main(String[] args) {
      /*
      System.out.println("==========Cat TreeSet ==============");
      TreeSet<Cat> ts = new TreeSet<Cat>();  
      ts.add(new Cat(2));  
      ts.add(new Cat(1));  
      ts.add(new Cat(3));  
      ts.add(new Cat(5));  
      ts.add(new Cat(4)); 
      ts.add(new Cat(4)); 
      System.out.println(ts.size());  //5       
      
      System.out.println("==========Dog TreeSet ==============");
      
      
      TreeSet<Dog> ts2 = new TreeSet<Dog>();  
      ts2.add(new Dog(2));  
      ts2.add(new Dog(1));  
      ts2.add(new Dog(3));  
      ts2.add(new Dog(5));  
      ts2.add(new Dog(4)); 
      ts2.add(new Dog(4)); 
      System.out.println(ts2.size());  //5
      */
      
      //添加到TreeSet，需要实现Comparable接口，即实现compareTo方法

      System.out.println("==========Tiger TreeSet ==============");
      
      
      TreeSet<Tiger> ts3 = new TreeSet<Tiger>();  
      ts3.add(new Tiger(2));  
      ts3.add(new Tiger(1));  
      ts3.add(new Tiger(3));  
      ts3.add(new Tiger(5));  
      ts3.add(new Tiger(4)); 
      ts3.add(new Tiger(4)); 
      System.out.println(ts3.size());  //5
   }

}
```

<p>Tips:</p>
<div align="center">
<img src="%E7%AC%AC%E5%8D%81%E7%AB%A0%E7%AC%94%E8%AE%B0%20Java%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84.assets/%E7%AC%94%E8%AE%B01.JPG"
style="zoom:50%;"/>
<img   src="%E7%AC%AC%E5%8D%81%E7%AB%A0%E7%AC%94%E8%AE%B0%20Java%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84.assets/%E7%AC%94%E8%AE%B02.JPG"
style="zoom:50%;"/>
<img  
src="%E7%AC%AC%E5%8D%81%E7%AB%A0%E7%AC%94%E8%AE%B0%20Java%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84.assets/%E7%AC%94%E8%AE%B03.JPG"
style="zoom:50%;"/>     
</div>

------

## 第五节 映射Map

## 映射

- Map映射
  - 数学定义：两个集合之间的元素对应关系
  - 一个输入对应到一个输出
  - {Key，Value}，键值对，K-V对
- Java中Map
  - Hashtable(同步，慢，数据量小)
  - HashMap(不支持同步，快，数据量大)
  - Properties(同步，文件格式，数据量小)

------

### Hashtable

- K-V对，K和V都不允许为null

- 同步，多线程安全

- 无序的

- 适合小数据量

- 主要方法：

  - clear 清空数据
  - containsValue 是否包含某一个值
  - contains 等同于containsValue
  - containsKey 是否包含某一个Key
  - get 根据key获取相应的值
  - put 增加新的K-V对
  - remove 删除某一个K-V对
  - size 返回数据大小

- 遍历：Enumeration

  ```java
  import java.util.Enumeration;
  import java.util.Hashtable;
  import java.util.Iterator;
  import java.util.Map;
  import java.util.Map.Entry;
  
  public class HashtableTest {
  
     public static void main(String[] args) {
        Hashtable<Integer,String> ht =new  Hashtable<Integer,String>();
        //ht.put(1, null); 运行报错
        //ht.put(null,1);  编译报错
        ht.put(1000, "aaa");
        ht.put(2, "bbb");
        ht.put(30000, "ccc");
        System.out.println(ht.contains("aaa"));
        System.out.println(ht.containsValue("aaa"));
        System.out.println(ht.containsKey(30000));
        System.out.println(ht.get(30000));
        
        ht.put(30000, "ddd");  //更新覆盖ccc
        System.out.println(ht.get(30000));
        
        ht.remove(2);
        System.out.println("size: " + ht.size());
        
        ht.clear();
        System.out.println("size: " + ht.size());
        
        
        Hashtable<Integer,String> ht2 =new  Hashtable<Integer,String>();
        for(int i=0;i<100000;i++)
        {
           ht2.put(i, "aaa");
        }
        traverseByEntry(ht2);
        traverseByKeySet(ht2);
        traverseByKeyEnumeration(ht2);    
     }
     
     public static void traverseByEntry(Hashtable<Integer,String> ht)
     {
        long startTime = System.nanoTime();
        System.out.println("============Entry迭代器遍历==============");
        Integer key;
        String value;
        Iterator<Entry<Integer, String>> iter = ht.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry<Integer, String> entry = iter.next();
            key = entry.getKey();// 获取key
            value = entry.getValue();// 获取value
            //System.out.println("Key:" + key + ", Value:" + value);
        }
        long endTime = System.nanoTime();
         long duration = endTime - startTime;
         System.out.println(duration + "纳秒");
     }
     
     
     public static void traverseByKeySet(Hashtable<Integer,String> ht)
     {
        long startTime = System.nanoTime();
        System.out.println("============KeySet迭代器遍历=============="); 
        Integer key;
        String value;
        Iterator<Integer> iter = ht.keySet().iterator();
        while(iter.hasNext()) {
            key = iter.next();        
            value = ht.get(key);// 获取value
            //System.out.println("Key:" + key + ", Value:" + value);
        }
        long endTime = System.nanoTime();
         long duration = endTime - startTime;
         System.out.println(duration + "纳秒");
     }
     
     
     public static void traverseByKeyEnumeration(Hashtable<Integer,String> ht)
     {
        long startTime = System.nanoTime();
        System.out.println("============KeyEnumeration迭代器遍历=============="); //只读
        Integer key;
        String value;
        Enumeration<Integer> keys = ht.keys();
        while(keys.hasMoreElements()) {
            key = keys.nextElement();   
            value = ht.get(key);  //获取value
            //System.out.println("Key:" + key + ", Value:" + value);
        }
        long endTime = System.nanoTime();
         long duration = endTime - startTime;
         System.out.println(duration + "纳秒");
     }
  }
  ```

### HashMap

- K-V对，允许为null

- 不同步，多线程不安全

  ```java
  Map m = Collections.synchronizedMap(new HashMap(……)) ；  //同步
  ```

- 无序的

- 主要方法：

  - clear 清空数据
  - containsValue 是否包含某一个值
  - contains 等同于containsValue
  - containsKey 是否包含某一个Key
  - get 根据key获取相应的值
  - put 增加新的K-V对
  - remove 删除某一个K-V对
  - size 返回数据大小
  
- 遍历：KeySet

```java
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class HashMapTest {

   public static void main(String[] args) {
      HashMap<Integer,String> hm =new  HashMap<Integer,String>();
      hm.put(1, null); 
      hm.put(null, "abc");  
      hm.put(1000, "aaa");
      hm.put(2, "bbb");
      hm.put(30000, "ccc");
      System.out.println(hm.containsValue("aaa"));
      System.out.println(hm.containsKey(30000));
      System.out.println(hm.get(30000));
      
      hm.put(30000, "ddd");  //更新覆盖ccc
      System.out.println(hm.get(30000));
      
      hm.remove(2);
      System.out.println("size: " + hm.size());
      
      hm.clear();
      System.out.println("size: " + hm.size());
      
      
      HashMap<Integer,String> hm2 =new  HashMap<Integer,String>();
      for(int i=0;i<100000;i++)
      {
         hm2.put(i, "aaa");
      }
      traverseByEntry(hm2);
      traverseByKeySet(hm2);    
   }
   
   public static void traverseByEntry(HashMap<Integer,String> ht)
   {
      long startTime = System.nanoTime();
      System.out.println("============Entry迭代器遍历==============");
      Integer key;
      String value;
      Iterator<Entry<Integer, String>> iter = ht.entrySet().iterator();
      while(iter.hasNext()) {
          Map.Entry<Integer, String> entry = iter.next();
          key = entry.getKey();// 获取key
          value = entry.getValue(); // 获取value
          //System.out.println("Key:" + key + ", Value:" + value);
      }
      long endTime = System.nanoTime();
       long duration = endTime - startTime;
       System.out.println(duration + "纳秒");
   }
   
   
   public static void traverseByKeySet(HashMap<Integer,String> ht)
   {
      long startTime = System.nanoTime();
      System.out.println("============KeySet迭代器遍历=============="); 
      Integer key;
      String value;
      Iterator<Integer> iter = ht.keySet().iterator();
      while(iter.hasNext()) {
          key = iter.next();        
          value = ht.get(key);// 获取value
          //System.out.println("Key:" + key + ", Value:" + value);
      }
      long endTime = System.nanoTime();
       long duration = endTime - startTime;
       System.out.println(duration + "纳秒");
   }
}
```

### LinkedHashMap

​      —基于双向链表的维持插入顺序的HashMap

​      —遍历：KeySet

```java
import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class LinkedHashMapTest {

   public static void main(String[] args) {
      LinkedHashMap<Integer,String> hm =new  LinkedHashMap<Integer,String>();
      hm.put(1, null); 
      hm.put(null, "abc");  
      hm.put(1000, "aaa");
      hm.put(2, "bbb");
      hm.put(30000, "ccc");
      System.out.println(hm.containsValue("aaa"));
      System.out.println(hm.containsKey(30000));
      System.out.println(hm.get(30000));
      
      hm.put(30000, "ddd");  //更新覆盖ccc
      System.out.println(hm.get(30000));
      
      hm.remove(2);
      System.out.println("size: " + hm.size());
      
      //hm.clear();
      //System.out.println("size: " + hm.size());
      
      System.out.println("遍历开始==================");
      
      Integer key;
      String value;
      Iterator<Entry<Integer, String>> iter = hm.entrySet().iterator();
      while(iter.hasNext()) {
          Map.Entry<Integer, String> entry = iter.next();
          key = entry.getKey();
          value = entry.getValue();
          System.out.println("Key:" + key + ", Value:" + value);
      }
      System.out.println("遍历结束==================");     
      
      LinkedHashMap<Integer,String> hm2 =new  LinkedHashMap<Integer,String>();
      for(int i=0;i<100000;i++)
      {
         hm2.put(i, "aaa");
      }
      traverseByEntry(hm2);
      traverseByKeySet(hm2);    
   }
   
   public static void traverseByEntry(LinkedHashMap<Integer,String> ht)
   {
      long startTime = System.nanoTime();
      System.out.println("============Entry迭代器遍历==============");
      Integer key;
      String value;
      Iterator<Entry<Integer, String>> iter = ht.entrySet().iterator();
      while(iter.hasNext()) {
          Map.Entry<Integer, String> entry = iter.next();
          key = entry.getKey();
          value = entry.getValue();
          //System.out.println("Key:" + key + ", Value:" + value);
      }
      long endTime = System.nanoTime();
       long duration = endTime - startTime;
       System.out.println(duration + "纳秒");
   }
   
   
   public static void traverseByKeySet(LinkedHashMap<Integer,String> ht)
   {
      long startTime = System.nanoTime();
      System.out.println("============KeySet迭代器遍历=============="); 
      Integer key;
      String value;
      Iterator<Integer> iter = ht.keySet().iterator();
      while(iter.hasNext()) {
          key = iter.next();        
          value = ht.get(key);
          //System.out.println("Key:" + key + ", Value:" + value);
      }
      long endTime = System.nanoTime();
       long duration = endTime - startTime;
       System.out.println(duration + "纳秒");
   }
}
```

### TreeMap

​      —基于红黑树的Map，可以根据key的自然排序或者compareTo方法进行排序输出

​      —遍历：Entry

```java
import java.util.TreeMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class TreeMapTest {

   public static void main(String[] args) {
      TreeMap<Integer,String> hm =new  TreeMap<Integer,String>();
      hm.put(1, null); 
      //hm.put(null, "abc");  运行报空指针异常  
      hm.put(1000, "aaa");
      hm.put(2, "bbb");
      hm.put(30000, "ccc");
      System.out.println(hm.containsValue("aaa"));
      System.out.println(hm.containsKey(30000));
      System.out.println(hm.get(30000));
      
      hm.put(30000, "ddd");  //更新覆盖ccc
      System.out.println(hm.get(30000));
      
      //hm.remove(2);
      System.out.println("size: " + hm.size());
      
      //hm.clear();
      //System.out.println("size: " + hm.size());
      
      System.out.println("遍历开始==================");
      
      Integer key;
      String value;
      Iterator<Entry<Integer, String>> iter = hm.entrySet().iterator();
      while(iter.hasNext()) {
          Map.Entry<Integer, String> entry = iter.next();
          key = entry.getKey();
          value = entry.getValue();
          System.out.println("Key:" + key + ", Value:" + value);
      }
      System.out.println("遍历结束==================");
      
      TreeMap<Integer,String> hm2 =new  TreeMap<Integer,String>();
      for(int i=0;i<100000;i++)
      {
         hm2.put(i, "aaa");
      }
      traverseByEntry(hm2);
      traverseByKeySet(hm2);    
   }
   
   public static void traverseByEntry(TreeMap<Integer,String> ht)
   {
      long startTime = System.nanoTime();
      System.out.println("============Entry迭代器遍历==============");
      Integer key;
      String value;
      Iterator<Entry<Integer, String>> iter = ht.entrySet().iterator();
      while(iter.hasNext()) {
          Map.Entry<Integer, String> entry = iter.next();
          key = entry.getKey();
          value = entry.getValue();
          //System.out.println("Key:" + key + ", Value:" + value);
      }
      long endTime = System.nanoTime();
       long duration = endTime - startTime;
       System.out.println(duration + "纳秒");
   }
   
   
   public static void traverseByKeySet(TreeMap<Integer,String> ht)
   {
      long startTime = System.nanoTime();
      System.out.println("============KeySet迭代器遍历=============="); 
      Integer key;
      String value;
      Iterator<Integer> iter = ht.keySet().iterator();
      while(iter.hasNext()) {
          key = iter.next();        
          value = ht.get(key);
          //System.out.println("Key:" + key + ", Value:" + value);
      }
      long endTime = System.nanoTime();
       long duration = endTime - startTime;
       System.out.println(duration + "纳秒");
   }
}
```

------

### Properties

- 继承于Hashtable
- 可以将K-V对保存在文件中
- 使用于数据量少的配置文件
- 继承自Hashtable的方法
- 从文件加载的load方法，写入到文件中的store方法
- 获取属性 getProperty；设置属性 setProperty

```java
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;

//基于Properties常用的操作
public class PropertiesTest {
    //根据Key读取Value
    public static String GetValueByKey(String filePath, String key) {
        Properties pps = new Properties();
        try {
            InputStream in = new BufferedInputStream (new FileInputStream(filePath));  
            pps.load(in); //所有的K-V对都加载了
            String value = pps.getProperty(key);
            //System.out.println(key + " = " + value);
            return value;
            
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    //读取Properties全部信息
    public static void GetAllProperties(String filePath) throws IOException {
        Properties pps = new Properties();
        InputStream in = new BufferedInputStream(new FileInputStream(filePath));
        pps.load(in); //所有的K-V对都加载了
        Enumeration en = pps.propertyNames(); //得到配置文件的名字
        
        while(en.hasMoreElements()) {
            String strKey = (String) en.nextElement();
            String strValue = pps.getProperty(strKey);
            //System.out.println(strKey + "=" + strValue);
        }
        
    }
    
    //写入Properties信息
    public static void WriteProperties (String filePath, String pKey, String pValue) throws IOException {
        File file = new File(filePath);
       if(!file.exists())
       {
          file.createNewFile();
       }
       Properties pps = new Properties();
        
        InputStream in = new FileInputStream(filePath);
        //从输入流中读取属性列表(键和元素树)
        pps.load(in);
        //调用Hashtable的方法put，使用getProperty提供并行性 
        //强制要求为属性的键和值使用字符串，返回值是Hashtable调用put的结果
        OutputStream out = new FileOutputStream(filePath);
        pps.setProperty(pKey, pValue);
        //以适合使用load方法加载到Properties表中的格式
        //将此Properties表中的属性列表(键和元素对)写入输出流
        pps.store(out, "Update " + pKey + " name");
        out.close();
    }
    
    public static void main(String [] args) throws IOException{
       System.out.println("写入Test.properties================");
        WriteProperties("Test.properties","name", "12345");
        
        System.out.println("加载Test.properties================");
        GetAllProperties("Test.properties");
        
        System.out.println("从Test.properties加载================");
        String value = GetValueByKey("Test.properties", "name");
        System.out.println("name is " + value);
    }
}
```

------

## 第六节 工具类

- JCF中工具类
  - 不存储数据，实现高效操作
    - 排序
    - 搜索
  - Arrays类
  - Collections类

------

### Arrays(数组处理)

- 排序：对数组排序，sort/parallelSort
- 查找：从数组中查找一个元素，binarySearch
- 批量拷贝：从源数组批量复制元素到目标数组，copyOf
- 批量赋值：对数组进行批量赋值，fill
- 等价性比较：判定两个数组内容是否相同，equals

```java
import java.util.Arrays;
import java.util.Random;

public class ArraysTest { 
   public static void main(String[] args) {
      testSort();
      testSearch();
      testCopy();
      testFill();
      testEquality();
   }
   public static void testSort() {
      Random r = new Random();
      int[] a = new int[10];
      for(int i=0;i<a.length;i++)    {
         a[i] = r.nextInt();  //Random.nextInt()   返回下一个int随机数
      }
      System.out.println("===============测试排序================");
      System.out.println("排序前");
      for(int i=0;i<a.length;i++)    {
         System.out.print(a[i] + ",");
      }
      System.out.println();
      System.out.println("排序后");
      Arrays.sort(a);
      for(int i=0;i<a.length;i++)    {
         System.out.print(a[i] + ",");
      }
      System.out.println();     
   }

   public static void testSearch() {
      Random r = new Random();
      int[] a = new int[10];
      for(int i=0;i<a.length;i++)
      {
         a[i] = r.nextInt();
      }
      a[a.length-1] = 10000;
      System.out.println("===========测试查找============");
      System.out.println("10000 ��λ����" + Arrays.binarySearch(a, 10000));
   }
   
   public static void testCopy() {
      Random r = new Random();
      int[] a = new int[10];
      for(int i=0;i<a.length;i++)
      {
         a[i] = r.nextInt();
      }
      int[] b = Arrays.copyOf(a, 5);
      System.out.println("===========测试拷贝前五个元素============");
      System.out.print("源数组:");
      for(int i=0;i<a.length;i++)
      {
         System.out.print(a[i] + ",");
      }
      System.out.println();
      System.out.print("目标数组:");
      for(int i=0;i<b.length;i++)
      {
         System.out.print(b[i] + ",");
      }
      System.out.println();
   }
   public static void testFill() {
      int[] a = new int[10];
      Arrays.fill(a, 100);
      Arrays.fill(a, 2, 8, 200);
      System.out.println("===========测试批量赋值============");
      System.out.print("数组赋值后:");
      for(int i=0;i<a.length;i++)
      {
         System.out.print(a[i] + ",");
      }
      System.out.println();
   }
   public static void testEquality() {
      int[] a = new int[10];
      Arrays.fill(a, 100);
      int[] b = new int[10];
      Arrays.fill(b, 100);      
      System.out.println(Arrays.equals(a, b));
      b[9] = 200;
      System.out.println(Arrays.equals(a, b));
   }
}
```

### Collections(包装器类)

- 排序：对List排序，sort
- 搜索：从List中搜索一个元素，binarySearch
- 批量赋值：对List进行批量赋值，fill
- 最大、最小：查找集合中最大/小值，max，min
- 反序：将List反序排列，reverse

```java
import java.util.ArrayList;
import java.util.Collections;

public class CollectionsTest {

   public static void main(String[] args) {
      ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(12);
        list.add(2);
        list.add(19);
         
        // 排序
        Collections.sort(list);
        // 检索
        System.out.println("元素所在的索引值为:" + Collections.binarySearch(list, 12));
        //最大最小
        System.out.println("最大值:" + Collections.max(list));
        System.out.println("最小值:" + Collections.min(list));
        Collections.reverse(list); //反序不需要先排序
         
        Collections.fill(list, 100); //全部赋值为100
   }
}
```

### 对象比较

**<u>自定义对象需要实现Comparable接口，按照compareTo方法所规定的原则进行排序</u>**

- 对象实现Comparable接口(需要修改对象类)
  - compareTo方法
    - < 返回 -1   ；  ==返回 0   ；  > 返回 1
  - Arrays和Collections在进行对象sort时，自动调用该方法
- 新建Comparator(适用于对象类不可更改的情况)
  - compare方法
    - < 返回 -1   ；  ==返回 0   ；  > 返回 1
  - Comparator比较器将作为参数提交给工具类的sort方法

```java
import java.util.Arrays;

public class Person implements Comparable<Person> {
	String name;
	int age;

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public int compareTo(Person another) {
		int i = 0;
		i = name.compareTo(another.name); // 使用字符串的比较
		if (i == 0) {
			// 如果名字一样，比较年龄，返回比较年龄结果结果
			return age - another.age;
		} else {
			return i; // 名字不一样，返回比较名字的结果
		}
	}

	public static void main(String... a) {
		Person[] ps = new Person[3];
		ps[0] = new Person("Tom", 20);
		ps[1] = new Person("Mike", 18);
		ps[2] = new Person("Mike", 20);

		Arrays.sort(ps);
		for (Person p : ps) {
			System.out.println(p.getName() + "," + p.getAge());
		}
	}
}
```

```java
public class Person2 {
   private String name;
    private int age;
   public String getName() {
      return name;
   }
   public int getAge() {
      return age;
   }

    public Person2(String name, int age)
    {
       this.name = name;
       this.age = age;
    }
}
```

```java
import java.util.Arrays;
import java.util.Comparator;

public class Person2Comparator  implements Comparator<Person2> {
   public int compare(Person2 one, Person2 another) {
      int i = 0;
      i = one.getName().compareTo(another.getName());
      if (i == 0) {
         // 如果名字一样，比较年龄，返回比较年龄结果结果
         return one.getAge() - another.getAge();
      } else {
         return i; // 名字不一样，返回比较名字的结果
      }
   }

   public static void main(String[] args) {
      // TODO Auto-generated method stub
      Person2[] ps = new Person2[3];
      ps[0] = new Person2("Tom", 20);
      ps[1] = new Person2("Mike", 18);
      ps[2] = new Person2("Mike", 20);

      Arrays.sort(ps, new Person2Comparator()); //调用sort时，定义一个比较器
      for (Person2 p : ps) {
         System.out.println(p.getName() + "," + p.getAge());
      }
   }
}
```

