# 第一章笔记 Maven

[TOC]

## 第一节 构建工具

### mvn仓库：https://mvnrepository.com/

### Maven方法

1. 创建Maven项目
2. 在mvn中央仓库中搜索第三方jar
3. 将工具包**依赖文本**拷贝到pom.xml中
4. Maven编译和运行
5. 程序运行

### Java构建工具

- 构建工具功能
  - 自动帮程序员甄别和下载第三方库(jar)
  - 完成整个项目编译(调用javac.exe)
  - 完成整个项目单元测试流程(调用Junit工具)
  - 完成项目打包(jar/war等格式，调用jar.exe)
  - ……
- 当前主要的Java构建工具
  - Maven，Gradle(安卓开发)，Ivy，Buildr，Ant等

------

## 第二节 Maven概念和实战

### Maven基础概念

- Maven开发流程
  - 新建Maven项目
  - 在中央仓库查找第三方jar的依赖文本
  - 拷贝依赖文本至项目的pom.xml
  - 执行maven build，编译/构建整个项目
- Maven是一个构建工具，自动下载中心仓库的 jar文件，存在本地进行管理，编译、测试、运行、打包发布Java项目

#### 阿里云国内Maven库地址：https://maven.aliyun.com/mvn/guide

**配置指南**

打开maven的配置文件(windows机器一般在maven安装目录的conf/settings.xml)，在`<mirrors></mirrors>`标签中添加mirror子节点:

```xml
<mirror>
    <id>aliyunmaven</id>
    <mirrorOf>*</mirrorOf>
    <name>阿里云公共仓库</name>
    <url>https://maven.aliyun.com/repository/public</url>
</mirror>
```

------

### Maven编译工作流程

![](%E7%AC%AC%E4%B8%80%E7%AB%A0%E7%AC%94%E8%AE%B0%20Maven.assets/Maven%E7%BC%96%E8%AF%91%E5%B7%A5%E4%BD%9C%E6%B5%81%E7%A8%8B.JPG)

------

### POM(Project Object Model)

- XML格式
- 包含了项目信息、依赖信息、构建信息
- 构件信息(artifact)
  - groupId    组织
  - artifactId    产品名称
  - version    版本

```xml
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-math3</artifactId>
    <version>3.6.1</version>
</dependency>
```

------

### Maven repository(仓库)

- Maven仓库存放和管理各种构件
  - 本地仓库(本地用户的.m2文件夹)
  - 远程仓库
    - 中央仓库
    - 阿里云仓库
    - 谷歌仓库
    - ……

------

### Maven项目的目录结构

基本目录结构

- src
  - main(业务程序)
    - java/ 存放java文件
    - resources/ 存放程序资源文件
  - test/(单元测试程序)
    - java/ 存放测试程序
    - resources/ 存放测试程序资源文件
- pom.xml

------

### 创建Maven项目

- 设置信息
  - groupId：组织名(包名)
  - artifactId：项目名
  - Name：别名(optional)
  - Description：描述(optional)

- 创建Maven项目的两种方法
  1. 直接创建Maven Project
  2. 先创建Java Project，再Convert to Maven Project，最后修改pom.xml，添加jar依赖

------

### Maven编译

- 打开maven项目窗口：工具栏-->Help(帮助)-->Find Action(查找操作)，在弹出的窗口里的文本框输入：maven projects

- 在打开的maven project窗口中，点击YourProject(你的maven项目名)->Lifecycle(生命周期)->package->Run Maven Build(运行Maven构建)执行打包，生成的war默认在工程的target目录下。

- 编译成功，控制台输出Build Success

