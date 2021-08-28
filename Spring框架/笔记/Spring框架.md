# Spring

[TOC]

## 简介

### Spring理念

使现有的技术更加容易使用，整合了现有的技术框架



- SSH：Struct2+Spring+HIbernate
- SSM：SpringMvc+Spring+Mybatis



```xml
<!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>5.3.9</version>
</dependency>

<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-jdbc</artifactId>
    <version>5.3.9</version>
</dependency>
```



### 特点

- 控制反转(IOC)，面向切面(AOP)
- 支持事务处理

------

## 组成

![](Spring%E6%A1%86%E6%9E%B6.assets/Spring%E6%A8%A1%E5%9D%97.JPG)

**核心容器（Spring Core）**

　　核心容器提供Spring框架的基本功能。Spring以bean的方式组织和管理Java应用中的各个组件及其关系。Spring使用BeanFactory来产生和管理Bean，它是工厂模式的实现。BeanFactory使用控制反转(IoC)模式将应用的配置和依赖性规范与实际的应用程序代码分开。

**应用上下文（Spring Context）**

　　Spring上下文是一个配置文件，向Spring框架提供上下文信息。Spring上下文包括企业服务，如JNDI、EJB、电子邮件、国际化、校验和调度功能。

**Spring面向切面编程（Spring AOP）**

　　通过配置管理特性，Spring AOP 模块直接将面向方面的编程功能集成到了 Spring框架中。所以，可以很容易地使 Spring框架管理的任何对象支持 AOP。Spring AOP 模块为基于 Spring 的应用程序中的对象提供了事务管理服务。通过使用 Spring AOP，不用依赖 EJB 组件，就可以将声明性事务管理集成到应用程序中。

**JDBC和DAO模块（Spring DAO）**

　　JDBC、DAO的抽象层提供了有意义的异常层次结构，可用该结构来管理异常处理，和不同数据库供应商所抛出的错误信息。异常层次结构简化了错误处理，并且极大的降低了需要编写的代码数量，比如打开和关闭链接。

**对象实体映射（Spring ORM）**

　　Spring框架插入了若干个ORM框架，从而提供了ORM对象的关系工具，其中包括了Hibernate、JDO和 IBatis SQL Map等，所有这些都遵从Spring的通用事物和DAO异常层次结构。

**Web模块（Spring Web）**

　　Web上下文模块建立在应用程序上下文模块之上，为基于web的应用程序提供了上下文。所以Spring框架支持与Struts集成，web模块还简化了处理多部分请求以及将请求参数绑定到域对象的工作。

**MVC模块（Spring Web MVC）**

　　MVC框架是一个全功能的构建Web应用程序的MVC实现。通过策略接口，MVC框架变成为高度可配置的。MVC容纳了大量视图技术，其中包括JSP、POI等，模型来有JavaBean来构成，存放于m当中，而视图是一个街口，负责实现模型，控制器表示逻辑代码，由c的事情。Spring框架的功能可以用在任何J2EE服务器当中，大多数功能也适用于不受管理的环境。Spring的核心要点就是支持不绑定到特定J2EE服务的可重用业务和数据的访问的对象，毫无疑问这样的对象可以在不同的J2EE环境，独立应用程序和测试环境之间重用。

------

## 拓展

### Spring Boot

- 基于SpringBoot可以快速开发单个微服务
- 约定大于配置

### Spring Cloud

- 基于SpringBoot实现

------

## IOC

### 理论推导

1. UserDao接口

   

2. UserDaoImpl实现类

   

3. UserService 业务接口

   

4. UserServiceImpl 业务实现类



#### 使用Set接口实现

**UserServiceImpl** 

```java
//private UserDao userDao=new UserDaoImpl;
private UserDao userDao;
//利用set进行动态值的注入
public void setUserDao(UserDao userDao){
    this.userDao=userDao;
}
```

- 之前程序是主动创建对象，控制权在程序员

- 使用set注入后，控制权在用户，程序变成了被动的接受对象

  

> 程序架构不变，系统耦合性大大降低，可以更专注的在业务的实现上，这是IOC原型

------

### IOC本质

**控制反转IoC(Inversion of Control)，是一种设计思想，DI(依赖注入)是实现IoC的一种方法**，也有人认为DI只是IoC的另一种说法。没有IoC的程序中 , 我们使用面向对象编程 , 对象的创建与对象间的依赖关系完全硬编码在程序中，对象的创建由程序自己控制，控制反转后将对象的创建转移给第三方，个人认为所谓控制反转就是：获得依赖对象的方式反转了。

**IoC是Spring框架的核心内容**，使用多种方式完美的实现了IoC，可以使用XML配置，也可以使用注解，新版本的Spring也可以零配置实现IoC。

Spring容器在初始化时先读取配置文件，根据配置文件或元数据创建与组织对象存入容器中，程序使用时再从Ioc容器中取出需要的对象

![](Spring%E6%A1%86%E6%9E%B6.assets/IOC%E5%AE%B9%E5%99%A8.png)

**控制反转是一种通过描述（XML或注解）并通过第三方去生产或获取特定对象的方式。在Spring中实现控制反转的是IoC容器，其实现方法是依赖注入（Dependency Injection,DI）。**

------

### IOC XML配置

```xml
<!--bean就是java对象 , 由Spring创建和管理

    id = 变量名
    class = new的对象
    property相当于给对象的属性设置一个值
    -->

    <bean id="hello" class="com.test.pojo.Hello">
        <property name="str" value="Spring"/>
    </bean>
```

```xml
    <bean id="MySQLImpl" class="com.test.dao.UserDaoMySQLImpl"/>
    <bean id="OracleImpl" class="com.test.dao.UserDaoOracleImpl"/>
    <bean id="UserServiceImpl" class="com.test.service.UserServiceImpl">
        <property name="userDao" ref="OracleImpl"/>
    </bean>     
    <!--ref  引用Spring容器中创建好的对象
             value 具体的值
        -->
```

- Hello 对象是由Spring创建的
- Hello 对象的属性是由Spring容器设置的

这个过程就叫控制反转 :

- 控制 : 谁来控制对象的创建 , 传统应用程序的对象是由程序本身控制创建的 , 使用Spring后 , 对象是由Spring来创建的
- 反转 : 程序本身不创建对象 , 而变成被动的接收对象 .

依赖注入 : 就是利用**<u>set方法</u>**来进行注入的.

IOC是一种编程思想，由主动的编程变成被动的接收

------

## 注解实现自动装配

- 自动装配是使用spring满足bean依赖的一种方法
- Spring会在应用上下文中为某个bean寻找其依赖的bean

使用注解须知

1. 导入约束，context约束
2. 配置注解的支持：<context:annotation-config/>

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xmlns:context="http://www.springframework.org/schema/context"
      xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config/>
</beans>
```

**autowire byName (按名称自动装配)**

当一个bean节点带有 autowire byName的属性时。

1. 将查找其类中所有的set方法名，例如setCat，获得将set去掉并且首字母小写的字符串，即cat。
2. 去spring容器中寻找是否有此字符串名称id的对象。
3. 如果有，就取出注入；如果没有，就报空指针异常。

**autowire byType (按类型自动装配)**

使用autowire byType首先需要保证：同一类型的对象，在spring容器中唯一。如果不唯一，会报不唯一的异常。

------

### @Autowired

- 直接在属性上使用，也可以在set方法上使用
- 如果@Autowired不能唯一自动装配上属性，则需要通过@Qualifier(value="   ")

```java
@Autowire()
private Cat cat;
@Autowired
private Dog dog;
@Autowired
public void setCat(Cat cat) {
this.cat = cat;
}
@Autowired
public void setDog(Dog dog) {
this.dog = dog;
}
```

### @Resource

```java
@Resource(name = "cat")
private Cat cat;
@Resource
private Dog dog;
```

### @Nullable

- 字段标记了这个注解，表示该字段可以为null

------

## 使用注解开发

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd">
    <!-- 指定要扫描的包，自动配置注解支持，这个包@Component组件下的注解生效，-->
    <context:component-scan base-package="com.test.pojo"/>
    <context:annotation-config/>
</beans>
```

### @Component

- 组件，放在类上，说明这个类被Spring管理，就变成了bean

```java
//等价于  <bean id="user" class="com.test.pojo.User"/>
//@Component组件
@Component
public class User {
    public String name="JOJO";
}
```

### 属性注入

```java
public String name;
// 相当于  <property name="name" value="JOJO"/>
@Value("JOJO")
public void setName(String name) {
this.name = name;
}
```

### @Component衍生注解

在web开发中，按照mvc三层架构分层

- dao[**@Repository**]
- service[**@Service**]
- controller[**@Controller**]

这三个注解与@Component功能相同，代表将某个类注入到Spring，装配bean

### @Scope

作用域

- singleton(单例模式)：对象个数全局唯一，Spring默认机制
- prototype(原型模式)：每次从容器中get时，都会生成一个新的对象

## XML与注解整合开发

- xml管理Bean
- 注解完成属性注入

------

## 代理模式

SpringAOP的底层

代理模式的分类：

- 静态代理
- 动态代理

### 静态代理

角色分析：

- 抽象角色(租房)：真实角色和代理角色共同要做的事即共性，一般使用接口或者抽象类解决
- 真实角色(房东)：被代理的角色
- 代理角色(中介)：代理真实角色后，一般会做一些附属操作
- 客户：访问代理角色进行一些操作

代码步骤：

1. 抽象角色

   ```java
   package com.test.demo01;
   
   //抽象角色 租房 接口
   public interface Renter {
       public void rent();
   }
   ```

2. 真实角色

   ```java
   package com.test.demo01;
   
   //真实角色 房东
   public class Landlord implements Renter{
   
       @Override
       public void rent() {
           System.out.println("房东出租房子");
       }
   }
   ```

3. 代理角色

   ```java
   package com.test.demo01;
   
   import static java.lang.Thread.sleep;
   
   public class Proxy implements Renter{
       private Landlord landlord; //组合
   
       public Proxy(){
       }
       public Proxy(Landlord landlord){
           this.landlord=landlord;
       }
   
       @Override
       public void rent() {
           landlord.rent();
           try {
               sleep(1000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           System.out.println("………………");
           try {
               sleep(3000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           seehouse();
           try {
               sleep(1000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           System.out.println("………………");
           try {
               sleep(3000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           hetong();
           try {
               sleep(1000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           System.out.println("………………");
           try {
               sleep(3000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           cost();
       }
       //合成复用原则
   
       public void seehouse(){
           System.out.println("中介带你看房");
       }
   
       public void hetong(){
           System.out.println("签署中介合同");
       }
   
       public void cost(){
           System.out.println("交费完成");
       }
   }
   
   ```

4. 客户端访问代理角色

   ```java
   package com.test.demo01;
   
   public class User {
       public static void main(String[] args) {
           //代理
           Proxy proxy=new Proxy(new Landlord());
           proxy.rent();
       }
   }
   ```

------

### 动态代理

动态代理的代理类是动态生成的

动态代理的本质就是使用**反射机制**实现

动态代理分为两大类：基于接口的动态代理，基于类的动态代理

- 基于接口：JDK动态代理
- 基于类：CGLib
- java字节码：javassist

------

**静态代理**

- 可以使得我们的真实角色更加纯粹 . 不再去关注一些公共的事情 .
- 公共的业务由代理来完成 . 实现了业务的分工 ,
- 公共业务发生扩展时变得更加集中和方便 .

- 类多了 , 多了代理类 , 工作量变大了 . 开发效率降低 .

**动态代理**

- 可以使得我们的真实角色更加纯粹 . 不再去关注一些公共的事情 .
- 公共的业务由代理来完成 . 实现了业务的分工 ,
- 公共业务发生扩展时变得更加集中和方便 .
- 一个动态代理 , 一般代理某一类业务
- 一个动态代理可以代理多个类，代理的是接口！



**静态代理代理的接口是写死的，只能代理该类接口的实现类，实现其对应的方法，真实角色都属于同一类，动态代理通过反射获取被代理对象的接口类型，所以代理的对象可以是任意的，代理类都可以通过反射获取接口类型，被代理对象的方法也可以通过反射获取，所以动态代理相比静态代理最大的区别就是能够代理的类型可以是任意（Object）的，而静态代理只能代理一类对象（实现相同的接口），静态代理实现了和被代理类相同的接口，而动态代理实现的是InvocationHandler ，可以通过反射实现任意类型的接口，更具灵活性。**

------

## AOP

- 横切关注点：跨越应用程序多个模块的方法或功能。即是，与我们业务逻辑无关的，但是我们需要关注的部分，就是横切关注点。如日志 , 安全 , 缓存 , 事务等等 ....
- 切面（ASPECT）：横切关注点 被模块化 的特殊对象。即，它是一个类。
- 通知（Advice）：切面必须要完成的工作。即，它是类中的一个方法。
- 目标（Target）：被通知对象。
- 代理（Proxy）：向目标对象应用通知之后创建的对象。
- 切入点（PointCut）：切面通知 执行的 “地点”的定义。
- 连接点（JointPoint）：与切入点匹配的执行点。

![](Spring%E6%A1%86%E6%9E%B6.assets/1363376-20210405100521632-535935317.png)

------

### 使用Spring实现AOP

方式一：通过 **Spring API接口** 实现【Spring API接口】

方式二：**自定义类**来实现Aop【切面定义】

方式三：使用**注解**实现

![](Spring%E6%A1%86%E6%9E%B6.assets/AOP%E5%AE%9E%E7%8E%B0%E6%9C%BA%E5%88%B6-1629215263136.JPG)

------

## 声明式事务

- 把一组业务当作一个业务：要么都成功，要么都失败
- 确保完成性和一致性

------

### 事务ACID属性

- 原子性（Atomicity）

事务是原子性操作，由一系列动作组成，事务的原子性确保动作要么全部完成，要么完全不起作用

- 一致性（Consistency）

一旦所有事务动作完成，事务就要被提交。数据和资源处于一种满足业务规则的一致性状态中

- 隔离性（Isolation）

可能多个事务会同时处理相同的数据，因此每个事务都应该与其他事务隔离开来，防止数据损坏

- 持久性（Durability）

事务一旦完成，无论系统发生什么错误，结果都不会受到影响。通常情况下，事务的结果被写到持久化存储器中

------

Spring中的事务管理

- 声明式事务
- 编程式事务

声明式事务

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/tx
        https://www.springframework.org/schema/tx/spring-tx.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-tx.aop">

    <!--data source-->
    <bean id="datasource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
        <property name="url" value="url=jdbc:mysql://localhost:3306/mybatis/>
        <property name="username" value="root"/>
        <property name="password" value="123456"/>
    </bean>

    <!--sqlsession-->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="datasource" />
        <!--bound mybatis-->
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
        <property name="mapperLocations" value="classpath:com/mapper/*.xml"/>
    </bean>

    <!--声明式事务-->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <constructor-arg ref="datasource" />
    </bean>

    <!--结合aop实现事务置入-->
    <!--配置事务的类-->
    <tx:advice id="tx1" transaction-manager="transactionManager">
        <!--给哪些方法配置事务-->
        <!--配置事务的传播特性-->
        <tx:attributes>
            <tx:method name="add" propagation="REQUIRED"/>
            <tx:method name="delete" propagation="REQUIRED"/>
            <tx:method name="update" propagation="REQUIRED"/>
            <tx:method name="*" propagation="REQUIRED"/>
            <tx:method name="query" read-only="true"/>
        </tx:attributes>
    </tx:advice>

    <!--配置事务切入-->
    <aop:config>
        <aop:pointcut id="txpointxut" expression="execution(* com.mapper.*.*(..))"/>
        <aop:advisor advice-ref="tx1" pointcut-ref="txpointxut"/>
    </aop:config>

</beans>
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <import resource="spring-dao.xml"/>

    <bean id="userMapper2" class="com.mapper.UserMapperIml2">
        <property name="sqlSessionFactory" ref="sqlSessionFactory"></property>
    </bean>

</beans>
```

**Mapper**

```java
package com.mapper;

import com.pojo.User;

import java.util.List;

public interface UserMapper {
    List<User> selectUser();
    int addUser(User user);
    int delete(int id);
}
```

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.mapper.UserMapper">
    <select id="selectUser" resultType="user">
        select * from mybatis.user;
    </select>

    <insert id="addUser" parameterType="user">
        insert into mybatis.user (id, name, pwd) values
        (#{id}, #{name}, #{pwd})
    </insert>

    <delete id="delete" parameterType="int">
        delete from mybatis.user where id=#{id}
    </delete>
</mapper>
```

```java
package com.mapper;

import com.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class UserMapperIml2 extends SqlSessionDaoSupport implements UserMapper {

    public List<User> selectUser() {
        User user = new User(6, "Ray", "JOJO");
        SqlSession sqlSession = getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.addUser(user);
        mapper.delete(6);
        return mapper.selectUser();
    }

    public int addUser(User user) {
        return getSqlSession().getMapper(UserMapper.class).addUser(user);
    }

    public int delete(int id) {
        return getSqlSession().getMapper(UserMapper.class).delete(id);
    }
}
```

