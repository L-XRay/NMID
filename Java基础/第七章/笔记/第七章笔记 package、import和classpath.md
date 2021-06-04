# 第七章笔记 package、import和classpath

[TOC]

- 所有的Java类都放置在同一目录下，相互调用无需显示声明调用

  —同一目录下，类名不能相同

  —文件过多，查找修改麻烦且容易出错

- Java支持多个目录放置Java，通过package/import/classpath/jar等机制配合使用，支持跨目录放置和调用Java类

## 第一节 package和import

### package

- Java类文件第一句话给出包的名称
- 类全称(长名称)=包名+类名；短名称=类名
- 引用类必须采用全程引用；程序正文可以用短名称

------

- 包名 package name尽量唯一
- 在Java中，常用域名做包名
- 域名逆序：cn.edu.cqupt，范围通常从大到小
- 类的完整名字：包名+类名，cn.edu.cqupt.PackageExample
- 包名：和目录层次一样，cn\edu\cqupt\PackageExample.java

<u>Tips：目录分隔符在Windows上是\，在Linux/Mac上是/，在Java程序中用/均可兼容</u>

------

### import

可用import关键字引入类

#### import规则

- import必须放在package之后，类定义之前

- 多个import的顺序无关

- 可以用*来引入一个目录下的所有类，但不包括该目录下的子目录文件

  即只能引入该目录下的类文件

- import尽量精确，以免新增的同名程序会使老程序报错

------

## 第二节 jar文件导出和导入

### jar

- jar文件，一种扩展名为jar的文件，是Java特有的一种文件格式，用于可执行文件的传播
- jar文件实际上是一组class文件的压缩包

#### jar文件优势

1. jar文件可以包括多个class，比多层目录更加简洁实用
2. jar文件经过压缩，只有一个文件，网络下载和传播更有优势
3. jar文件只包括class，没有包含java文件，不会泄露源码
4. 将多个class文件压缩成jar文件(只有一个文件)，可以规定给一个版本号，更容易进行版本控制

#### jar导出

[](https://blog.csdn.net/branwel/article/details/79918018?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522162268349216780357230226%2522%252C%2522scm%2522%253A%252220140713.130102334.pc%255Fall.%2522%257D&request_id=162268349216780357230226&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~first_rank_v2~hot_rank-1-79918018.first_rank_v2_pc_rank_v29&utm_term=idea%E6%89%93%E5%87%BAjar&spm=1018.2226.3001.4187)

#### jar导入

[](https://blog.csdn.net/Golden_soft/article/details/80952243?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522162268403216780264045705%2522%252C%2522scm%2522%253A%252220140713.130102334.pc%255Fall.%2522%257D&request_id=162268403216780264045705&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~first_rank_v2~hot_rank-2-80952243.first_rank_v2_pc_rank_v29&utm_term=idea%E5%AF%BC%E5%85%A5jar%E5%8C%85&spm=1018.2226.3001.4187)

------

## 第三节 package和import——命令行

### 运行命令

- java   -classpath   .;c:\temp     cn.com.test.Man

  1. **<u>java</u>**，执行命令，是java.exe的简写

  2. **<u>-classpath</u>**，固定格式参数，可简写为-cp

  3. **<u>.;c:\temp</u>**  是一个(Windows分号，Linux/Mac冒号连接起来的字符串)

     按分隔符分开，得到一个个子路径。

  ![](%E7%AC%AC%E4%B8%83%E7%AB%A0%E7%AC%94%E8%AE%B0%20package%E3%80%81import%E5%92%8Cclasspath.assets/%E7%AC%94%E8%AE%B01.JPG)

### 编译和运行规则

- **编译一个类，需要java文件的全路径**，包括扩展名

- **运行一个类，需写类名全称(非文件路径)**，无需写扩展名

- 编译类，需要给出这个类所依赖的类(包括依赖的类再次依赖的所有其他类)所在的路径

- 运行类，需要给出这个类，以及所依赖类的路径总和

- classpath参数也可以包含jar包。若路径内有空格，需将classpath参数整体加双引号

  > 如：java   -classpath   ".;c:\test.jar;c:\temp;c:\a bc"   cn.com.test.Man

------

## 第四节 Java访问权限

### 四种Java访问权限

- private：私有的，只能本类访问
- default(通常省略不写)：同一包内访问
- protected：同一个包，子类均可访问
- public：公开的，所有类都可以访问

**适用范围：**

- 都可以修饰成员变量、成员方法、构造函数
- default和public可以修饰类

|           | 同一个类 | 同一个包 | 不同包的子类 | 不同包的非子类 |
| :-------: | :------: | :------: | :----------: | :------------: |
|  private  |    √     |          |              |                |
|  default  |    √     |    √     |              |                |
| protected |    √     |    √     |      √       |                |
|  public   |    √     |    √     |      √       |       √        |

