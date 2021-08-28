# JavaWeb

[TOC]

## 基本概念

### web应用程序

- 一个web应用由多部分组成(静态web，动态web)
  - html，css，js
  - jsp，servlet
  - Java程序
  - jar包
  - 配置文件(Properties)

------

## web服务器

### ASP(动态服务器页面)

- 在HTML中嵌入VB的脚本，ASP+COM

### PHP

- 开发速度快，跨平台(70%，WP)
- 无法承载大访问量

### JSP/Servlet

- B/S：浏览和服务器
- C/S：客户端和服务器

------

## Tomcat

不会就先模仿

- 将自己写的网站，放到服务器(Tomcat)中指定的web应用的文件夹(webapps)，就可以访问

### 网站结构

```java
---webapps:Tomcat服务器的web目录
    --ROOT
    --TEST:网站的目录名
        -WEB-INF
           -classes:java程序
           -lib:web应用所依赖的jar包
           -web.xml:网站配置文件
        -index.html:默认的首页
        -static
           -css
               -style.css
           -js
           -img
        -……
```

[Apache Tomcat Examples](http://localhost:8080/examples/)

------

## Servlet

- 开发一个Servlet程序
  - 编写一个类，实现Servlet接口
  - 把开发好的Java类部署到web服务器中

> 把实现了Servlet接口的**Java程序**叫做Servlet
>
> Servlet有两个默认的实现类：HttpServlet，GenericServlet

### HelloServlet

1. 构建一个Maven项目，删掉里面的src目录，在项目里建立Module，这个空的工程就是Maven主工程

2. 关于Maven项目父子工程的理解

   父项目中会有

   ```xml
   <modules>
           <module>test01</module>
   </modules>
   ```

   子项目中会有

   ```xml
   <parent>
        <artifactId>Servlet</artifactId>
        <groupId>com.test</groupId>
        <version>1.0-SNAPSHOT</version>
   </parent>
   ```

3. Maven环境优化

   1. 修改web.xml为最新
   2. 将maven结构搭建完整

4. 编写一个Servlet程序

   1. 编写一个普通类
   2. 实现Servlet接口

   ```java
   package com.test.servlet;
   
   import javax.servlet.ServletException;
   import javax.servlet.ServletOutputStream;
   import javax.servlet.http.HttpServlet;
   import javax.servlet.http.HttpServletRequest;
   import javax.servlet.http.HttpServletResponse;
   import java.io.IOException;
   import java.io.PrintWriter;
   
   public class HelloServlet extends HttpServlet {
   
       //由于get或者post只是请求实现的不同的方式，可以相互调用，业务逻辑都一样
       @Override
       protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           //ServletOutputStream outputStream=resp.getOutputStream();
           PrintWriter writer = resp.getWriter();//响应流
           writer.print("Hello,Servlet");
   
       }
   
       @Override
       protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           doGet(req, resp);
       }
   }
   ```

5. 编写Servlet的映射

   ```xml
       <!--注册Servlet-->
   <servlet>
       <servlet-name>hello</servlet-name>
       <servlet-class>com.test.servlet.HelloServlet</servlet-class>
   </servlet>
       <!--Servlet的请求路径-->
   <servlet-mapping>
       <servlet-name>hello</servlet-name>
       <url-pattern>hello</url-pattern>
   </servlet-mapping>
   ```

6. 配置Tomcat

7. 启动测试

------

## Http

### Http请求

- 客户端——发请求(Request)——服务器

  百度：

  ```java
  Request URL: https://www.baidu.com/     请求地址
  Request Method: GET    //get方法/post方法
  Status Code: 200 OK    //状态码:200
  Remote Address: 183.232.231.174:443  //远程地址
  ```

  ```java
  Accept: text/html
  Accept-Encoding: gzip, deflate, br
  Accept-Language: zh-CN,zh;q=0.9    //语言
  Connection: keep-alive
  ```

#### 请求行

- 请求行中的请求方式：GET
- 请求方式：Get，Post，HEAD，DELETE，PUT，TRACT……
  - get：请求能够携带的参数较少，大小有限制，会在浏览器的URL地址栏显示数据内容，不安全但高效
  - post：请求能够携带的参数没有限制，大小没有限制，不会在浏览器的URL地址栏显示数据内容，安全但不高效

#### 消息头

```java
Accept:告诉浏览器所支持的数据类型
Accept-Encoding:支持的编码格式  GBK  UTF-8  GB2312
Accept-Language:语言环境
Cache-Control:缓存控制
Connection: 请求完成后的连接情况
HOST:主机……/.
```

### Http响应

- 服务器——响应——客户端

  百度：

  ```java
  Cache-Control: private       //缓存控制
  Connection: keep-alive       //连接
  Content-Encoding: gzip       //编码
  Content-Type: text/html;charset=utf-8      //类型
  ```

#### 响应体

```java
Accept:告诉浏览器所支持的数据类型
Accept-Encoding:支持的编码格式  GBK  UTF-8  GB2312
Accept-Language:语言环境
Cache-Control:缓存控制
Connection: 请求完成后的连接情况
HOST:主机……/.
Refresh:刷新频率
Location:网页重新定位
```

#### 响应状态码

- 200：请求响应成功
- 3xx：请求重定向
  - 重定向：重新到新位置
- 4xx：资源不存在  404
- 5xx：服务器代码错误   500   502：网关错误
