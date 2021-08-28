# Mybatis

[TOC]

- MyBatis 是一款优秀的**持久层框架**
- 它支持定制化 SQL、存储过程以及高级映射。
- MyBatis 避免了几乎所有的 JDBC 代码和手动设置参数以及获取结果集。
- MyBatis 可以使用简单的 XML 或注解来配置和映射原生类型、接口和 Java 的 POJO（Plain Old Java Objects，普通老式 Java 对象）为数据库中的记录。

------

## 持久化

### 数据持久化

- 持久化就是将程序的数据在持久状态和瞬时状态转化的过程
- 内存：**断电即失**
- 数据库(Jdbc)，io文件持久化。
- 生活：冷藏. 罐头。

### 持久层

Dao层，Service层，Controller层….

- 完成持久化工作的代码块
- 层界限十分明显。

------

## 环境配置

### Maven依赖

```xml
<dependencies>
    
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>3.5.7</version>
    </dependency>

    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
    </dependency>
    
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.26</version>
    </dependency>

</dependencies>
```

### Mybatis核心配置文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">

<configuration>

    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/mybatis"/>
                <property name="username" value="root"/>
                <property name="password" value="123456"/>
            </dataSource>
        </environment>
    </environments>

    <mappers>
        <mapper resource="com/test/dao/userMapper.xml"/>
    </mappers>
    
</configuration>
```

### Mapper配置文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--namespace=绑定一个对应的Mapper接口-->
<mapper namespace="com.test.dao.UserMapper">
<!--select查询语句-->
    <select id="getUserList" resultType="com.test.pojo.User">
        select * from mybatis.user
    </select>

</mapper>
```

------

## 编写代码

### 实体类

```java
package com.test.pojo;

//实体类
public class User {
    private int id;
    private String name;
    private String password;

    public User() {
    }
    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
```

### 工具类

```java
package com.test.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtils {

    private static  SqlSessionFactory sqlSessionFactory;

    static{
        try {
            //使用Mybatis第一步：获取sqlSessionFactory对象
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory= new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // SqlSession 完全包含了面向数据库执行 SQL 命令所需的所有方法。
    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }

}
```

### 接口

```java
package com.test.dao;

import com.test.pojo.User;

import java.util.List;

public interface UserMapper {
    List<User> getUserList();
}
```

## CRUD(增删改查)

### namespace

namespace中的包名要和 Dao/mapper 接口的包名一致

### select

选择，查询语句;

- id : 就是对应的namespace中的方法名
- resultType：SQL语句执行的返回值
- parameterType ： 参数类型

1. 编写接口

   ```java
   //根据ID查询用户
   User getUserByID(int id);
   ```

2. 编写对应的mapper中的sql语句

   ```java
       <select id="getUserByID" parameterType="int" resultType="com.test.pojo.User">
           select * from mybatis.user where id=#{id}
       </select>
   ```

3. 测试

   ```java
       @Test
       public void getUserByIDTest(){
           SqlSession session= MybatisUtils.getSqlSession();
           UserMapper userMapper=session.getMapper(UserMapper.class);
           User user=userMapper.getUserByID(2);
           System.out.println(user);
           session.close();
       }
   ```

### Insert

```xml
    <insert id="AddUser" parameterType="com.test.pojo.User">
        insert into mybatis.user (id,name,password) values (#{id},#{name},#{password})
    </insert>
```

### update

```xml
    <update id="UpdateUser" parameterType="com.test.pojo.User">
        update mybatis.user set id=#{id},password=#{password} where name=#{name}
    </update>
```

### Delete

```xml
    <delete id="DeleteUser" parameterType="int">
        delete from mybatis.user where id=#{id}
    </delete>
```

注意点：

- **增删改需要提交事务：session.commit();**

------

### 万能Map

假设，我们的实体类，或者数据库中的表，字段或者参数过多，我们应当考虑使用Map

```java
//万能的Map
int AddUser2(Map<String,Object> map);
```

```java
<!--对象中的属性，可以直接取出来    传递map的key-->
<insert id="AddUser2" parameterType="map">
    insert into mybatis.user (id,name,password) values (#{userid},#{username},#{pwd})
</insert>
```

```java
    @Test
    public void AddUserTest2(){
        SqlSession session= MybatisUtils.getSqlSession();
        UserMapper userMapper=session.getMapper(UserMapper.class);
        Map<String,Object> map=new HashMap<String, Object>();

        map.put("userid",4);
        map.put("username","夏");
        map.put("pwd","335945");

        userMapper.AddUser2(map);
        session.commit();
        session.close();
    }
```

Map传递参数，直接在sql中取出key即可！ 【parameterType="map"】

对象传递参数，直接在sql中取对象的属性即可！【parameterType="Object"】

只有一个基本类型参数的情况下，可以直接在sql中取到！

多个参数用Map，**或者注解！**

------

### 模糊查询

1. Java代码执行的时候，传递通配符 % %

   ```java
   List<User> userList = mapper.getUserLike("%李%");
   ```

2. 在sql拼接中使用通配符！

   ```java
   select * from mybatis.user where name like "%"#{value}"%"
   ```

------

## 配置解析

### 核心配置文件

- mybatis-config.xml

- MyBatis 的配置文件包含了会深深影响 MyBatis 行为的设置和属性信息。

  ```xml
  configuration（配置）
  properties（属性）
  settings（设置）
  typeAliases（类型别名）
  typeHandlers（类型处理器）
  objectFactory（对象工厂）
  plugins（插件）
  environments（环境配置）
  environment（环境变量）
  transactionManager（事务管理器）
  dataSource（数据源）
  databaseIdProvider（数据库厂商标识）
  mappers（映射器）
  ```

------

### 环境配置（environments）

MyBatis 可以配置成适应多种环境

**不过要记住：尽管可以配置多个环境，但每个 SqlSessionFactory 实例只能选择一种环境**

Mybatis默认的事务管理器就是 JDBC ， 连接池 ： POOLED

------

### 属性（properties）

编写一个配置文件

db.properties

```properties
driver=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://localhost:3306/mybatis
username=root
password=123456
```

在核心配置文件中映入

```xml
    <!--引入外部配置文件-->
    <properties resource="db.properties">
        <property name="username" value="root"/>
        <property name="pwd" value="11111"/>
    </properties>
```

- 可以直接引入外部文件
- 可以在其中增加一些属性配置
- 如果两个文件有同一个字段，优先使用外部配置文件的

------

### 类型别名（typeAliases）

可以给实体类起别名-

```xml
    <typeAliases>
        <typeAlias type="com.test.pojo.User" alias="user"/>
    </typeAliases>
```

也可以指定一个包名，MyBatis 会在包名下面搜索需要的 Java Bean，比如：

扫描实体类的包，它的默认别名就为这个类的 类名，首字母小写！

```xml
<typeAliases>
    <package name="com.test.pojo"/>
</typeAliases>
```

在实体类比较少的时候，使用第一种方式。

如果实体类十分多，建议使用第二种。

第一种可以DIY别名，第二种则·不行·，如果非要改，需要在实体上增加注解

```java
@Alias("user")
public class User {}
```

------

### 映射器（mappers）

MapperRegistry：注册绑定我们的Mapper文件；

方式一： 【推荐使用】

```xml
<!--每一个Mapper.XML都需要在Mybatis核心配置文件中注册！-->
<mappers>
    <mapper resource="com/test/dao/UserMapper.xml"/>
</mappers>
```

方式二：使用class文件绑定注册

```xml
<!--每一个Mapper.XML都需要在Mybatis核心配置文件中注册！-->
<mappers>
    <mapper class="com.test.dao.UserMapper"/>
</mappers>
```

注意点：

- 接口和他的Mapper配置文件必须同名！
- 接口和他的Mapper配置文件必须在同一个包下！

方式三：使用扫描包进行注入绑定

```xml
<!--每一个Mapper.XML都需要在Mybatis核心配置文件中注册！-->
<mappers>
    <package name="com.test.dao"/>
</mappers>
```

注意点：

- 接口和他的Mapper配置文件必须同名！
- 接口和他的Mapper配置文件必须在同一个包下！

------

## ResultMap

结果集映射

```
id   name   pwd
id   name   password
```

```xml
<!--结果集映射-->
<resultMap id="UserMap" type="User">
    <!--column数据库中的字段，property实体类中的属性-->
    <result column="id" property="id"/>
    <result column="name" property="name"/>
    <result column="pwd" property="password"/>
</resultMap>

<select id="getUserById" resultMap="UserMap">
    select * from mybatis.user where id = #{id}
</select>
```

- `resultMap` 元素是 MyBatis 中最重要最强大的元素
- ResultMap 的设计思想是，对于简单的语句根本不需要配置显式的结果映射，而对于复杂一点的语句只需要描述它们的关系就行了。
- `ResultMap` 最优秀的地方在于，虽然你已经对它相当了解了，但是根本就不需要显式地用到他们。
- 如果世界总是这么简单就好了。

## 使用注解开发

1. 注解在接口上实现

   ```java
   @Select("select * from user")
   List<User> getUsers();
   ```

2. 需要再核心配置文件中绑定接口！

   ```xml
   <!--绑定接口-->
   <mappers>
       <mapper class="com.test.dao.UserMapper"/>
   </mappers>
   ```

3. 测试

本质：反射机制实现

底层：动态代理

![](Mybatis.assets/%E5%8A%A8%E6%80%81%E4%BB%A3%E7%90%86.png)

## Mybatis详细的执行流程

![](Mybatis.assets/Mybatis%E6%89%A7%E8%A1%8C%E6%B5%81%E7%A8%8B.png)

------

## CRUD注解

我们可以在工具类创建的时候实现自动提交事务！

```java
public static SqlSession  getSqlSession(){
    return sqlSessionFactory.openSession(true);
}
```

编写接口，增加注解

```java
public interface UserMapper {

    @Select("select * from Mybatis.user")
    List<User> getUsers();

    // 方法存在多个参数，所有的参数前面必须加上 @Param("id")注解
    @Select("select * from user where id = #{id}")
    User getUserByID(@Param("id") int id);

    @Insert("insert into user(id,name,password) values (#{id},#{name},#{password})")
    int addUser(User user);

    @Update("update user set name=#{name},password=#{password} where id = #{id}")
    int updateUser(User user);

    @Delete("delete from user where id = #{uid}")
     int deleteUser(@Param("uid") int id);

}
```

测试类

【注意：我们必须要讲接口注册绑定到我们的核心配置文件中！】

**关于@Param() 注解**

- 基本类型的参数或者String类型，需要加上
- 引用类型不需要加
- 如果只有一个基本类型的话，可以忽略，但是建议大家都加上！
- 我们在SQL中引用的就是我们这里的 @Param() 中设定的属性名！

------

## 动态SQL

**什么是动态SQL：动态SQL就是指根据不同的条件生成不同的SQL语句**

```xml
动态 SQL 元素和 JSTL 或基于类似 XML 的文本处理器相似。在 MyBatis 之前的版本中，有很多元素需要花时间了解。MyBatis 3 大大精简了元素种类，现在只需学习原来一半的元素便可。MyBatis 采用功能强大的基于 OGNL 的表达式来淘汰其它大部分元素。

if
choose (when, otherwise)
trim (where, set)
foreach
```

### IF

```xml
<select id="selectBlogByIF" parameterType="map" resultType="blog">
    select * from mybatis.blog <!--  where 1=1  -->
    <where>
        <if test="title != null">
            and title = #{title}
        </if>
        <if test="author != null">
            and author = #{author}
        </if>
    </where>
</select>
```

### choose (when, otherwise)

```xml
 <select id="selectBlogByChoose" parameterType="map" resultType="blog">
        select * from mybatis.blog
        <where>
            <choose>
                <when test="title!=null">
                    title=#{title};
                </when>
                <when test="author!=null">
                    and author=#{author};
                </when>
                <otherwise>
                    and views=#{views};
                </otherwise>
            </choose>
        </where>
</select>
```

### trim (where,set)

```xml
<update id="updateBlog" parameterType="map">
    update mybatis.blog
        <set>
            <if test="id!=null">
                id=#{id},
            </if>
            <if test="author!=null">
                author=#{author},
            </if>
        </set>
    where title=#{title}
</update>
```

### SQL片段

有的时候，我们可能会将一些功能的部分抽取出来，方便复用！

1. 使用SQL标签抽取公共的部分

   ```xml
   <sql id="if-title-author">
       <if test="title != null">
           title = #{title}
       </if>
       <if test="author != null">
           and author = #{author}
       </if>
   </sql>
   ```

2. 在需要使用的地方使用Include标签引用即可

   ```xml
   <select id="selectBlogByIF" parameterType="map" resultType="blog">
       select * from mybatis.blog
       <where>
           <include refid="if-title-author"></include>
       </where>
   </select>
   ```

注意事项：

- 最好基于单表来定义SQL片段！
- 不要存在where标签

------

## foreach

```xml
<select id="selectBlogByForeach" parameterType="map" resultType="blog">
    select * from mybatis.blog
          <where>
              <foreach collection="ids" item="id" open="and (" close=")" separator="or">
                  id=#{id}
              </foreach>
          </where>
</select>
```

```java
@Test
public void SelectBlogByForeach(){
    SqlSession session=MybatisUtils.getSqlSession();
    BlogMapper mapper=session.getMapper(BlogMapper.class);
    HashMap map=new HashMap();
    ArrayList<Integer> ids=new ArrayList<Integer>();
    ids.add(1);
    ids.add(2);
    ids.add(3);
    map.put("ids",ids);

    List<Blog> blogs = mapper.selectBlogByForeach(map);
    for (Blog blog:blogs){
        System.out.println(blog);
    }
    session.close();
}
```
