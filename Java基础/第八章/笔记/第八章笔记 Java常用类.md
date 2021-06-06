# 第八章笔记 Java常用类

[TOC]

## 第一节 Java类库概述

### Java类库文档

——https://docs.oracle.com/javase/8/docs/api/

- 这些文档原先是程序中的注释。利用JavaDoc技术，将这些注释抽取出来，组织形成的以HTML为表现形式的API(应用编程接口)文档

### Java类库列表

- Java类库

  —包名以Java开始的包是Java核心包(Java Core Package)

  —包名以Javax开始的包是Java扩展包(Java Extension Package)

|      包名       |                             说明                             |
| :-------------: | :----------------------------------------------------------: |
|  java.applet.*  |                 提供了创建applet需要的所有类                 |
|   java.awt.*    |        提供了创建用户界面以及绘制和管理图形、图像的类        |
|  java.beans.*   |              提供了开发Java Beans 需要的所有类               |
|    java.io.*    |  提供了通过数据流、对象序列以及文件系统实现的系统输入、输出  |
|   java.lang.*   |                    Java编程语言的基本类库                    |
|   java.math.*   |         提供了简明的整数算术以及十进制算数的基本函数         |
|   java.net.*    |              提供了用于实现网络通讯应用的所有类              |
|   java.nio.*    |                 提供用于实现nio应用的所有类                  |
|   java.rmi.*    |               提供了与远程方法调用相关的所有类               |
| java.security.* |              提供了设计网络安全方案需要的一些类              |
|   java.sql.*    |         提供了访问和处理来自于Java标准数据源数据的类         |
|   java.text.*   | 包括以一种独立于自然语言的方式处理文本、日期、数字和消息的类和接口 |
|   java.time.*   |                     提供Java日期、时间类                     |
|   java.util.*   |    包括集合类、时间处理模式、日期时间工具等各类常用工具包    |

------

## 第二节 数字相关类

### Java数字类

- 整数 Short，Int，Long
- 浮点数 Float，Double
- 大数类 BigInteger(大整数)，BigDecimal(大浮点数)
- 随机数类 Random
- 工具类 Math

#### 大数字类

- 大整数类 BigInteger

  —支持无限大的整数运算

- 大浮点数 BigDecimal

  —支持无线大的小数运算

  —注意精度和截断

#### 随机数类

- Random随机数

  —nextInt()  返回一个随机int

  —nextInt(int a)  返回一个[0,a)之间的随机int

  —nextDouble()  返回一个[0.0,1.0]之间随机double

  —ints方法批量返回随机数数组

- Math.random()返回一个[0.0,1.0]之间随机的double

#### 数字工具类

- java.lang.Math

  —绝对值函数abs

  —对数函数log

  —比较函数max、min

  —幂函数pow

  —四舍五入函数round等

  —向下取整floor

  —向上取整ceil

------

## 第三节 字符串相关类

### 字符串

#### String

- java中使用频率最高的类
- 是一个不可变对象，加减操作性能较差
- 常用方法：charAt，concat，contains，endsWith，equals，equalsIgnoreCase，hashCode，indexOf，length，matches，replace，replaceAll，split，startsWith，subString，trim，valueOf

```java
public class StringTest {

	public static void main(String[] args) {
		String a = "123;456;789;123 ";
		System.out.println(a.charAt(0)); // 返回第0个元素		
        System.out.println(a.indexOf(";")); // 返回第一个;的位置
		System.out.println(a.concat(";000")); // 连接一个新字符串并返回，a不变
		System.out.println(a.contains("000")); // 判断a是否包含000
		System.out.println(a.endsWith("000")); // 判断a是否以000结尾
		System.out.println(a.equals("000")); // 判断是否等于000
		System.out.println(a.equalsIgnoreCase("000"));// 判断在忽略大小写情况下是否等于000
		System.out.println(a.length()); // 返回a长度
		System.out.println(a.trim()); // 返回a去除前后空格后的字符串，a不变
		String[] b = a.split(";"); // 将a以;为分隔符分割成数组
		for (int i = 0; i < b.length; i++) {
			System.out.println(b[i]);
		}

		System.out.println("===================");

		System.out.println(a.substring(2, 5)); // 截取a第2个到第5个字符，a不变
		System.out.println(a.replace("1", "a"));
		System.out.println(a.replaceAll("1", "a")); // replaceAll第一个参数是正则表达式

		System.out.println("===================");

		String s1 = "12345?6789";
		String s2 = s1.replace("?", "a");
		String s3 = s1.replaceAll("[?]", "a");
		// 这里的[?] 才表示字符问号，这样才能正常替换。不然在正则中会有特殊意义导异常
		System.out.println(s2);
		System.out.println(s3);
		System.out.println(s1.replaceAll("[\\d]", "a")); 
        
	}
}
```

#### 可变字符串

##### —StringBuffer

同步    线程安全   修改快速

##### —StringBulider

- 不同步   线程不安全   修改更快

- StringBuffer/StringBulider：方法一样
  - append/insert/delete/replace/substring
  - length 字符串实际大小，capacity字符串占用空间的大小
  - trimToSize()：去除空隙，将字符串存储压缩到实际大小

------

## 第四节 时间相关类

- java.sql.Date(和数据库对应的时间类)

- Calendar是目前程序中最常用的，但是**抽象类**

  —Calendar gc=Calendar.getInstance();

  —Calendar gc=new GregorianCalendar();

  —简单工厂模式

------

### Canlendar

- ~~get(Field)  获取时间中每个属性的值。Tips：月份0-11~~
- ~~getTime() 返回相应的Date对象~~
- ~~getTimeInMillis()  返回自1970.1.1以来的毫秒数~~

- ~~set(Field) 设置时间字段~~
- ~~add(field,amount) 根据指定字段增加/减少时间~~
- ~~roll(field,amount) 根据指定字段增加/减少时间,但不影响上一级的时间段~~

|       函数名       |                       说明                       |
| :----------------: | :----------------------------------------------: |
|     get(Field)     |      获取时间中每个属性的值。Tips：月份0-11      |
|     getTime()      |                返回相应的Date对象                |
| getTimeInMillis()  |            返回自1970.1.1以来的毫秒数            |
|     set(Field)     |                   设置时间字段                   |
| add(field,amount)  |            根据指定字段增加/减少时间             |
| roll(field,amount) | 根据指定字段增加/减少时间,但不影响上一级的时间段 |

```java
import java.util.Calendar;

public class CalendarTest {
	
	Calendar calendar = Calendar.getInstance();
	
	public void test1() {
        int year = calendar.get(Calendar.YEAR);// 获取年
        
        int month = calendar.get(Calendar.MONTH) + 1;// 获取月，获取月份时要+1
        
        int day = calendar.get(Calendar.DAY_OF_MONTH);// 获取日

        int hour = calendar.get(Calendar.HOUR);// 获取时
        // int hour = calendar.get(Calendar.HOUR_OF_DAY); // 24小时表示
        
        int minute = calendar.get(Calendar.MINUTE);// 获取分
        
        int second = calendar.get(Calendar.SECOND);// 获取秒

        int weekday = calendar.get(Calendar.DAY_OF_WEEK);// 星期，从星期天开始计算

        System.out.println("现在是" + year + "年" + month + "月" + day + "日" + hour
                + "ʱ" + minute + "分" + second + "秒" + "星期" + weekday);
    }

    // 一年后的今天
    public void test2() {
        // ͬ一月后的今天calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.YEAR, 1);

        int year = calendar.get(Calendar.YEAR);
        
        int month = calendar.get(Calendar.MONTH) + 1;
        
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        System.out.println("一年后的今天:" + year + "年" + month + "月" + day + "日");
    }

    // 获取任意一个月的最后一天
    public void test3() {
        // 假设6月
        int currentMonth = 6;
        //先求出7月的第一天
        // 1
        calendar.set(calendar.get(Calendar.YEAR), currentMonth, 1);

        calendar.add(Calendar.DATE, -1);

        // 获取日
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        System.out.println("6月最后一天为" + day + "号");
    }

    // 设置日期
    public void test4() {
        calendar.set(Calendar.YEAR, 2000);
        System.out.println("现在是" + calendar.get(Calendar.YEAR) + "年");

        calendar.set(2018, 7, 8);
        
        int year = calendar.get(Calendar.YEAR);
        
        int month = calendar.get(Calendar.MONTH)+1;

        int day = calendar.get(Calendar.DAY_OF_MONTH);

        System.out.println("现在是:" + year + "年" + month + "月" + day + "日");
    }
    
    //add和roll的用法
    public void test5() {     

        calendar.set(2018, 7, 8);
        calendar.add(Calendar.DAY_OF_MONTH, -8);
        
        int year = calendar.get(Calendar.YEAR);

        int month = calendar.get(Calendar.MONTH)+1;

        int day = calendar.get(Calendar.DAY_OF_MONTH);

        System.out.println("2018.8.8, 用add减少8天为:" + year + "." + month + "." + day);
        
        calendar.set(2018, 7, 8);
        calendar.roll(Calendar.DAY_OF_MONTH, -8);
        
        year = calendar.get(Calendar.YEAR);
       
        month = calendar.get(Calendar.MONTH)+1;
       
        day = calendar.get(Calendar.DAY_OF_MONTH);

        System.out.println("2018.8.8,用roll减少8天为:" + year + "." + month + "." + day);
    }
    //2018.8.31
    
	public static void main(String[] args) {
		CalendarTest c = new CalendarTest();
		c.test1();
		System.out.println("============");
		c.test2();
		System.out.println("============");
		c.test3();
		System.out.println("============");
		c.test4();
		System.out.println("============");
		c.test5();

	}

}
```

------

### java.time包

- 不变性，在多线程环境下
- 遵循设计模式，可扩展性强
- 新的Java日期/时间API的基础包

------

- java.time.chrono包：为非ISO的日历系统定义了一些泛化的API
- java.time.format包：格式化和解析日期时间对象的类
- java.time.temporal包：包含一些时态对象，可用其找出关于日期/时间对象的某个特定日期或时间
- java.time.zone包：包含支持不同时区以及相关规则的类

------

#### java.time包主要类

1. LocalDate：日期类
2. LocalTime：时间类(时分秒-纳秒)
3. LocalDateTime：LocalDate+LocalTime
4. Instant：时间戳

------

##### LocalDate

```java
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;

public class LocalDateExample {

    public static void main(String[] args) {
 
        //当前时间
        LocalDate today = LocalDate.now();
        System.out.println("Current Date="+today);
 
        //根据指定时间创建LocalDate
        LocalDate firstDay_2014 = LocalDate.of(2014, Month.JANUARY, 1);
        System.out.println("Specific Date="+firstDay_2014);
 
        //给定错误时间参数，将报异常java.time.DateTimeException
        //LocalDate feb29_2014 = LocalDate.of(2014, Month.FEBRUARY, 29);
 
        //可以更改时区
        LocalDate todayBeijing = LocalDate.now(ZoneId.of("Asia/Shanghai"));
        System.out.println("Current Date in Shanghai="+todayBeijing);
 
        //从纪元日01/01/1970开始365天 
        LocalDate dateFromBase = LocalDate.ofEpochDay(365);
        System.out.println("365th day from base date= "+dateFromBase);
 
        //2014年的第100天 
        LocalDate hundredDay2014 = LocalDate.ofYearDay(2014, 100);
        System.out.println("100th day of 2014="+hundredDay2014);
    }
 
}
```

##### LocalTime

```java
import java.time.LocalTime;
import java.time.ZoneId;

public class LocalTimeExample {
 
    public static void main(String[] args) {
 
        //当前时间 时分秒 纳秒
        LocalTime time = LocalTime.now();
        System.out.println("Current Time="+time);
 
        //指定时分秒创建对象
        LocalTime specificTime = LocalTime.of(12,20,25,40);
        System.out.println("Specific Time of Day="+specificTime);
 
        //错误的时间参数 程序报错DateTimeException
        //LocalTime invalidTime = LocalTime.of(25,20);
 
        //上海时间
        LocalTime timeSH = LocalTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println("Current Time in SH="+timeSH);
 
        
        //一天当中第几秒
        LocalTime specificSecondTime = LocalTime.ofSecondOfDay(10000);
        System.out.println("10000th second time= "+specificSecondTime);
 
    }
 
}
```

##### LocalDateTime

```java
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZoneOffset;
 
public class LocalDateTimeExample {
 
    public static void main(String[] args) {
 
        //当前日期 时分秒
        LocalDateTime today = LocalDateTime.now();
        System.out.println("Current DateTime="+today);
 
        //根据日期，时分秒创建对象
        today = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        System.out.println("Current DateTime="+today);
 
        //ָ指定具体时间创建对象
        LocalDateTime specificDate = LocalDateTime.of(2014, Month.JANUARY, 1, 10, 10, 30);
        System.out.println("Specific Date="+specificDate);
 
        //时间不对，程序报错DateTimeException
        //LocalDateTime feb29_2014 = LocalDateTime.of(2014, Month.FEBRUARY, 28, 25,1,1);
        
        //上海时区
        LocalDateTime todayShanghai = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println("Current Date in Shanghai="+todayShanghai);
 
         
        //从纪元日01/01/1970开始10000秒
        LocalDateTime dateFromBase = LocalDateTime.ofEpochSecond(10000, 0, ZoneOffset.UTC);
        System.out.println("10000th second time from 01/01/1970= "+dateFromBase); 
    } 
}
```

##### Instant

```java
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
 
public class InstantExample {
 
    public static void main(String[] args) {
        //当前时间戳
        Instant timestamp = Instant.now();
        System.out.println("Current Timestamp = "+timestamp);
 
        //从毫秒数创建时间戳
        Instant specificTime = Instant.ofEpochMilli(timestamp.toEpochMilli());
        System.out.println("Specific Time = "+specificTime);
 
        Date date = Date.from(timestamp); 
        System.out.println("current date = " + date);
    }
 
}
```

#### DataUtil

```java
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.TemporalAdjusters;
 
public class DateUtil {
 
    public static void main(String[] args) {
 
        LocalDate today = LocalDate.now();
 
        //判断是否是否闰年
        System.out.println("Year "+today.getYear()+" is Leap Year  "+today.isLeapYear());
 
        //今天和01/01/2015比较
        System.out.println("Today is before 01/01/2015  "+today.isBefore(LocalDate.of(2015,1,1)));
 
        //当前时分秒
        System.out.println("Current Time="+today.atTime(LocalTime.now()));
 
        //加减时间
        System.out.println("10 days after today will be "+today.plusDays(10));
        System.out.println("3 weeks after today will be "+today.plusWeeks(3));
        System.out.println("20 months after today will be "+today.plusMonths(20));
 
        System.out.println("10 days before today will be "+today.minusDays(10));
        System.out.println("3 weeks before today will be "+today.minusWeeks(3));
        System.out.println("20 months before today will be "+today.minusMonths(20));
 
        //调整时间
        System.out.println("First date of this month= "+today.with(TemporalAdjusters.firstDayOfMonth()));
        LocalDate lastDayOfYear = today.with(TemporalAdjusters.lastDayOfYear());
        System.out.println("Last date of this year= "+lastDayOfYear);
 
        //时间段计算
        Period period = today.until(lastDayOfYear);
        System.out.println("Period Format= "+period);
        System.out.println("Months remaining in the year= "+period.getMonths());        
    }
}
```

------

## 第五节 格式化相关类

### java.text包java.text.Format的子类

- NumberFormat：数字格式化，抽象类

  - DecimalFormat 工厂模式

- MessageFormat：字符串格式化

  —支持多个参数-值对位复制文本

  —支持变量的自定义格式

- DateFormat：日期/时间格式化，抽象类

  - SimpleDateFormat 工厂模式
  - parse：将字符串格式化为时间对象
  - format：将时间对象格式化为字符串

### Java.time.format包下

- DateTimeFormatter
  - ofPattern：设定时间格式
  - parse：将字符串格式化为时间对象
  - format：将时间对象格式化为字符串

```java
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatterTest {

   public static void main(String[] args) {
      //将字符串转化为时间
      String dateStr= "2016年10月25日";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        LocalDate date= LocalDate.parse(dateStr, formatter);
        System.out.println(date.getYear() + "-" + date.getMonthValue() + "-" + date.getDayOfMonth());
        
        System.out.println("==========================");
        
        //日期转换为字符串输出
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy年MM月dd日 hh:mm:ss");
        String nowStr = now.format(format);
        System.out.println(nowStr);


   }

}
```
