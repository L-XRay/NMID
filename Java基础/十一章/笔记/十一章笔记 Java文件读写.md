# 十一章笔记 Java文件读写

[TOC]

## 第一节 文件系统及Java文件基本操作

### 文件概述

- 文件系统是由OS(操作系统)管理的
- 文件系统和Java进程是平行的，是两套系统
- 文件系统是由文件夹和文件递归组合而成
- 文件目录分隔符
  - Linux/Unix  用/隔开
  - Windows  用\隔开，在程序中用/或  \ \ 代替
- 文件包括文件里面的内容和文件基本属性
- 文件基本属性：名称、大小、扩展名、修改时间等

------

### Java 文件类File

- java.io.File是文件和目录的重要类
- 常用方法
  - createNewFile    创建新文件
  - delete    删除文件或目录
  - exists    判断File对象是否存在
  - getAbsolutePath
  - getName    获取文件名字
  - getParent    获取上一层目录路径
  - getPath    获取当前文件的全路径
  - isDirectory    是否是目录
  - isFile    是否是文件
  - length    获取文件的大小bytes
  - listFiles    列出当前目录的所有子文件，不包括子目录下的文件
  - mkdir    创建一级(一层)目录
  - mkdirs    创建多级(多层)目录
  - lastModified    返回文件的最后一次修改时间

**<u>Tips：File不涉及具体的文件内容，只涉及属性</u>**

```java

import java.io.*;
public class FileAttributeTest{
  public static void main(String[] args){
	File d=new File("c:/temp");  //  创建目录
	if(!d.exists())
	{
		d.mkdirs();  //mkdir 创建单级目录  mkdirs 连续创建多级目录
	}
	System.out.println("Is d directory? " + d.isDirectory());
	 
    File f=new File("C:/temp/abc.txt"); // 创建文件
    if(!f.exists())
    {    	
      try
      {
        f.createNewFile(); //创建abc.txt
      }
      catch(IOException e){ 
    	  e.printStackTrace();
      }
    }  
    
    //输出文件相关属性
    System.out.println("Is f file? " + f.isFile());
    System.out.println("Name: "+f.getName());
    System.out.println("Parent: "+f.getParent());
    System.out.println("Path: "+f.getPath());
    System.out.println("Size: "+f.length()+" bytes");
    System.out.println("Last modified time: "+f.lastModified()+"ms");
    
    //遍历d目录下所有的文件信息，不会递归包括子目录下的文件
    System.out.println("list files in d directory");
    File[] fs = d.listFiles();  // 列出d目录下所有的子文件，不包括子目录下的文件
    for(File f1:fs)
    {
    	System.out.println(f1.getPath());
    }
    
    //f.delete(); //删除此文件
    //d.delete(); //删除此目录
  }
}
```

------

### Java NIO包

- java.io.File的有益补充

  - 文件复制和移动

  - 文件相对路径

  - 递归遍历目录

  - 递归删除目录

    ……

- 新的文件系统类

  - Path    和java.io.File基本相似
  - Files
  - DirectoryStream
  - FileVisitor
  - FileSystem

#### Path

```java
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathTest {
   public static void main(String[] args) {
      // 获得Path方法一:c:/temp/abc.txt
      Path path = FileSystems.getDefault().getPath("c:/temp", "abc.txt");
      System.out.println(path.getNameCount());

      // 获得Path方法二:用File的toPath()方法获得Path对象
      File file = new File("c:/temp/abc.txt");
      Path pathOther = file.toPath();
      // 0,说明这两个path相同
      System.out.println(path.compareTo(pathOther));

      // 获得path方法三
      Path path3 = Paths.get("c:/temp", "abc.txt");
      System.out.println(path3.toString());

      // 合并两个path
      Path path4 = Paths.get("c:/temp");
      System.out.println("path4: " + path4.resolve("abc.txt"));
       //path3和path4都代表着同一路径的文件"c:\temp\abc.txt
       
      if (Files.isReadable(path)) {  //文件是否可以读取
         System.out.println("it is readable");
      } else {
         System.out.println("it is not readable");
      }
   }
}
```

#### Files

```java
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

public class FilesTest {
	
	public static void main(String[] a)
	{
		moveFile();
		fileAttributes();
		createDirectory();
	}

	public static void moveFile() {
		Path from = Paths.get("c:/temp", "abc.txt");//  获取源文件

		Path to = from.getParent().resolve("test/def.txt");//获取目标文件
        //移动 c:/temp/abc.txt 到 c:/temp/test/def.txt，若目标文件存在，就替换
		try {
			System.out.println(Files.size(from));// 文件的大小bytes
            //调用移动方法，若目标文件存在，就替换
			Files.move(from, to, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			System.err.println("移动文件出错" + e.getMessage());
		}
	}
	
	
	public static void fileAttributes(){
		Path path = Paths.get("c:/temp");
		//1
		System.out.println(Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS));
		//2
		try {
			//获得文件的基础属性
			BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
			System.out.println(attributes.isDirectory());// isDirectory() 是否是目录
			System.out.println(new Date(attributes.lastModifiedTime().toMillis()).toLocaleString());// lastModifiedTime() 最后修改时间
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void createDirectory(){
		Path path = Paths.get("c:/temp/test");
		try {
			//创建文件夹
			if(Files.notExists(path)){  //File.notExists  测试此路径所在的文件是否不存在
				Files.createDirectories(path);
				System.out.println("create dir");
			}else{
				System.out.println("dir exists");
			}
			Path path2 = path.resolve("A.java"); 
			Path path3 = path.resolve("B.java");
			Path path4 = path.resolve("C.txt");
			Path path5 = path.resolve("D.jpg");
			Files.createFile(path2);
			Files.createFile(path3);
			Files.createFile(path4);
			Files.createFile(path5); //创建文件
			
			//不加条件遍历
			DirectoryStream<Path> paths = Files.newDirectoryStream(path);
			for(Path p : paths){
				System.out.println(p.getFileName());
			}
			System.out.println();
			
			//带有过滤器的遍历
			DirectoryStream<Path> pathsFilter = Files.newDirectoryStream(path, "*.{java,txt}");
			for(Path p : pathsFilter){
				System.out.println(p.getFileName());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
```

#### SearchJPG

```java
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;

class Search implements FileVisitor {

   private final PathMatcher matcher;

   public Search(String ext) {
      matcher = FileSystems.getDefault().getPathMatcher("glob:" + ext);
   }

   public void judgeFile(Path file) throws IOException {
      Path name = file.getFileName();
      if (name != null && matcher.matches(name)) {
         // 找到文件
         System.out.println("Searched file was found: " + name + " in " + file.toRealPath().toString());
      }
   }

   @Override
   public FileVisitResult postVisitDirectory(Object dir, IOException exc) throws IOException {
      System.out.println("Visited: " + (Path) dir);
      return FileVisitResult.CONTINUE;   // postVisitDirectory访问目录后调用
   }

   @Override
   public FileVisitResult preVisitDirectory(Object dir, BasicFileAttributes attrs) throws IOException {
      return FileVisitResult.CONTINUE;  // preVisitDirectory 访问目录前调用
   }

   @Override
   public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
      judgeFile((Path) file);
      return FileVisitResult.CONTINUE;// visitFile 访问文件时调用
   }

   @Override
   public FileVisitResult visitFileFailed(Object file, IOException exc) throws IOException {
      // report an error if necessary
      return FileVisitResult.CONTINUE;// visitFileFailed 访问文件失败调用
   }
}

public class SearchJPGFiles {

   public static void main(String[] args) throws IOException {

      String ext = "*.jpg";
      Path fileTree = Paths.get("C:/temp/");
      Search walk = new Search(ext);
      EnumSet<FileVisitOption> opts = EnumSet.of(FileVisitOption.FOLLOW_LINKS);

      Files.walkFileTree(fileTree, opts, Integer.MAX_VALUE, walk);
       // Files.walkFileTree 访问文件树中的每一个文件
   }
}
```

------

## 第二节 Java io 包概述

Java读写文件，只能以数据流的形式进行读写

- java.io包中
  - 节点类：直接对文件进行读写
  - 包装类：
    - 转化类：字节/字符/数据类型的转化类
    - 装饰类：装饰节点类

------

### 节点类(直接操作文件类)

- InputStream，OutputStream(字节)
  - FileInputStream，FileOutputStream
- Reader，Writer(字符)
  - FileReader，FileWriter

### 转换类(字符到字节之间的转化)

- InputStreamReader：文件读取时，字节转化为Java能理解的字符
- OutputStreamWriter：Java将字符转化为字节输入到文件中

### 装饰类(装饰节点类)

- DataInputStream，DataOutputStream：封装数据流
- BufferedInputStream，BufferOutputStream：缓存字节流
- BufferedReader，BufferedWriter：缓存字符流

------

## 第三节 文本文件读写

### 写文件

- 先创建文件，写入数据，关闭文件
- FileOutputStream(往文件写字节)，OutPutStreamWriter(字节和字符转化)，BufferedWriter(写缓存区类，加速写操作)
- BufferedWriter
  - write
  - newLine
- try-resource语句，自动关闭资源
- 关闭最外层的数据流，会把其上所有数据流关闭

![](%E5%8D%81%E4%B8%80%E7%AB%A0%E7%AC%94%E8%AE%B0%20Java%E6%96%87%E4%BB%B6%E8%AF%BB%E5%86%99.assets/%E7%AC%94%E8%AE%B0.JPG)

```java
import java.io.*;

public class TxtFileWrite {
   public static void main(String[] args) {
      writeFile1();
      System.out.println("===================");
   }

   public static void writeFile1() {
      FileOutputStream fos = null;
      OutputStreamWriter osw = null;
      BufferedWriter bw = null;
      try {
         fos = new FileOutputStream("c:/temp/abc.txt"); // 节点类
         osw = new OutputStreamWriter(fos, "UTF-8"); // 转化类
         //osw = new OutputStreamWriter(fos); 
         bw = new BufferedWriter(osw); // 装饰类
         // br = new BufferedWriter(new OutputStreamWriter(new
         // FileOutputStream("c:/temp/abc.txt")))
         bw.write("我们是");
         bw.newLine();
         bw.write("Ecnuers.^^");
         bw.newLine();
      } catch (Exception ex) {
         ex.printStackTrace();
      } finally {
         try {
            bw.close();  //关闭最后一个类，会将所有的底层流关闭
         } catch (Exception ex) {
            ex.printStackTrace();
         }
      }
   }

   public static void writeFile2() {
      //try-resource 语句，自动关闭资源
      try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("c:/temp/abc.txt")))) {
         bw.write("我们是");
         bw.newLine();
         bw.write("Ecnuers.^^");
         bw.newLine();
      } catch (Exception ex) {
         ex.printStackTrace();
      }
   }

}
```

### 读文件

- 先打开文件，逐行读入数据，关闭文件
- FileInputStream(在文件读字节)，InputStreamWriter(字节和字符转化)，BufferedReader(写缓存区类，加速读操作)
- BufferedReader
  - readLine
- try-resource语句，自动关闭资源
- 关闭最外层的数据流，会把其上所有数据流关闭

```java
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class TxtFileRead {
   public static void main(String[] args) {
      readFile1();
      System.out.println("===================");
   }

   public static void readFile1() {
      FileInputStream fis = null;
      InputStreamReader isr = null;
      BufferedReader br = null;
      try {
         fis = new FileInputStream("c:/temp/abc.txt"); 
         isr = new InputStreamReader(fis, "UTF-8"); 
         //isr = new InputStreamReader(fis);
         br = new BufferedReader(isr); 
         // br = new BufferedReader(new InputStreamReader(new
         // FileInputStream("c:/temp/abc.txt")))
         String line;
         while ((line = br.readLine()) != null) // 每次读取一行
         {
            System.out.println(line);
         }
      } catch (Exception ex) {
         ex.printStackTrace();
      } finally {
         try {
            br.close();
         } catch (Exception ex) {
            ex.printStackTrace();
         }
      }
   }

   public static void readFile2() {
      String line;
      //try-resource 自动关闭资源
      try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("c:/temp/abc.txt")))) {
         while ((line = in.readLine()) != null) {
            System.out.println(line);
         }
      }
      catch(Exception ex)
      {
         ex.printStackTrace();
      }
   }
}
```

------

## 第四节 二进制文件读写

### 写文件

- 先创建文件，写入数据，关闭文件
- FileOutputStream，BufferedOutputStream，DataOutputStream
- DataOutputStream
  - flush
  - write/writeBoolean/writeByte/writeChars/writeDouble/writeInt/WriteUTF……

- try-resource语句，自动关闭资源
- 关闭最外层的数据流，会把其上所有数据流关闭

![](%E5%8D%81%E4%B8%80%E7%AB%A0%E7%AC%94%E8%AE%B0%20Java%E6%96%87%E4%BB%B6%E8%AF%BB%E5%86%99.assets/%E7%AC%94%E8%AE%B01.JPG)

```java
import java.io.*;
public class BinFileWrite{
  public static void main(String[] args) throws Exception{
   writeFile();
    System.out.println("done.");
  }
  
  public static void writeFile() {
     FileOutputStream fos = null;
     DataOutputStream dos = null;
     BufferedOutputStream bos = null;
      try {
         fos = new FileOutputStream("c:/temp/def.dat"); // 节点类
         bos = new BufferedOutputStream(fos); // 装饰类
         dos = new DataOutputStream(bos); // 转化类   
         
         dos.writeUTF("a");
         dos.writeInt(20);
         dos.writeInt(180);
         dos.writeUTF("b");
      } catch (Exception ex) {
         ex.printStackTrace();
      } finally {
         try {
            dos.close();  //  关闭最后一个类，会将所有的底层流关闭
         } catch (Exception ex) {
            ex.printStackTrace();
         }
      }
   }
}
```

### 读文件

- 先打开文件，逐行读入数据，关闭文件
- FileInputStream，BufferedInputStream，DataInputStream
- DataOutputStream
  - read/readBoolean/readByte/readChars/readDouble/readInt/readUTF……
- try-resource语句，自动关闭资源
- 关闭最外层的数据流，会把其上所有数据流关闭

```java
import java.io.*;
public class BinFileRead{
  public static void main(String[] args) throws Exception{
     readFile();
  }
  public static void readFile() {
      //try-resource 自动关闭资源
      try (DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream("c:/temp/def.dat")))) {
         String a, b;
          int c, d;
          a=dis.readUTF();
          c=dis.readInt();
          d=dis.readInt();
          b=dis.readUTF();
          System.out.println("a: "+a);
          System.out.println("c: "+c);
          System.out.println("d: "+d);
          System.out.println("b: "+b);
      }
      catch(Exception ex)
      {
         ex.printStackTrace();
      }
   }
}
```

------

## 第五节 Zip文件读写

### Java zip 包

- Java zip 包支持Zip和Gzip包的压缩和解压
- zip文件操作类：java.util.zip包中
  - java.io.InputStream,java.io.OutputStream的子类
  - ZipInputStream,ZipOutputStream压缩文件输入/输出流
  - Zip压缩项

### 压缩

- 单个/多个压缩
  1. 打开输出zip文件
  2. 添加一个ZipEntry
  3. 打开一个输入文件，读数据，向ZipEntry写数据，关闭输入文件
  4. 重复步骤，即可写入多个文件到zip文件中
  5. 关闭zip文件

#### 单个

```java
import java.io.File ;  
import java.io.FileInputStream ;  
import java.io.InputStream ;  
import java.util.zip.ZipEntry ;  
import java.util.zip.ZipOutputStream ;  
import java.io.FileOutputStream ;  
public class SingleFileZip{  
    public static void main(String args[]) throws Exception{    
        File file = new File("c:/temp/abc.txt") ;  // 定义要压缩的文件  
        File zipFile = new File("c:/temp/single2.zip") ;   // 定义压缩文件名称  
        
        InputStream input = new FileInputStream(file) ; // 定义文件的输入流 
        ZipOutputStream zipOut = null ; // 声明压缩流对象 
        zipOut = new ZipOutputStream(new FileOutputStream(zipFile)) ;  
        zipOut.putNextEntry(new ZipEntry(file.getName())) ; // 设置ZipEntry对象  
        zipOut.setComment("single file zip") ;  // 设置注释 
        
        //压缩过程
        int temp = 0 ;  
        while((temp=input.read())!=-1){ // 读取内容 
            zipOut.write(temp) ;    // 压缩输出 
        }  
        input.close() ; // 关闭输入流 
        zipOut.close() ;    // 关闭输出流
        
        System.out.println("single file zip done.");
    }  
}
```

#### 多个

```java
//文件夹压缩
import java.io.File ;
import java.io.FileInputStream ;
import java.io.InputStream ;
import java.util.zip.ZipEntry ;
import java.util.zip.ZipOutputStream ;
import java.io.FileOutputStream ;
public class MultipleFileZip{
   public static void main(String args[]) throws Exception{   // 所有异常抛出
      File file = new File("c:/temp/multiple") ; // 定义要压缩的文件夹
      File zipFile = new File("c:/temp/multiple2.zip") ; // 定义压缩文件名称
      
      InputStream input = null ; // 定义文件输入流流
      ZipOutputStream zipOut = null ;    // 声明压缩流对象
      zipOut = new ZipOutputStream(new FileOutputStream(zipFile)) ;
      zipOut.setComment("multiple file zip") ;   // 设置注释
      
      //开始压缩
      int temp = 0 ;
      if(file.isDirectory()){    // 判断是否是文件夹
         File lists[] = file.listFiles() ;  // 列出全部子文件
         for(int i=0;i<lists.length;i++){
            input = new FileInputStream(lists[i]) ;    // 定义文件的输入流
            zipOut.putNextEntry(new ZipEntry(file.getName()
               +File.separator+lists[i].getName())) ; // 设置ZipEntry对象
            System.out.println("正在压缩" + lists[i].getName());
            while((temp=input.read())!=-1){    // 读取内容
               zipOut.write(temp) ;   // 压缩输出
            }
            input.close() ;    // 关闭输入流
         }
      }
      zipOut.close() ;   // 关闭输出流
      System.out.println("multiple file zip done.");
   }
}
```

------

### 解压

- 单个/多个解压
  1. 打开输入的zip文件
  2. 获取下一个ZipEntry
  3. 新建一个目标文件，从ZipEntry读取数据，向目标文件写入数据，关闭目标文件
  4. 重复步骤，即可从zip包中读取数据到多个目标文件
  5. 关闭zip文件

#### 单个

```java
import java.io.File ;  
import java.io.FileInputStream ;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry ;  
import java.util.zip.ZipInputStream ;   
public class SingleFileUnzip{  
    public static void main(String args[]) throws Exception{     
        //待解压文件，需要从zip文件打开输入流，读取数据到java中
       File zipFile = new File("c:/temp/single.zip") ;   // 定义压缩文件名称        
        ZipInputStream input = null ;   // 定义压缩输入流  
        input = new ZipInputStream(new FileInputStream(zipFile)) ;  // 实例化ZipInputStream  
        ZipEntry entry = input.getNextEntry() ; // 得到一个压缩实体  
        System.out.println("压缩实体名称:" + entry.getName()) ;  // 获取压缩包中文件名字
        
        //新建目标文件，需要从目标文件打开输出流，数据从java流入
        File outFile = new File("c:/temp/" + entry.getName());
        OutputStream out = new FileOutputStream(outFile) ;   // 实例化文件输出流
        int temp = 0 ;  
        while((temp=input.read())!=-1){  
            out.write(temp) ;  
        }  
        input.close() ;     // 关闭输入流
        out.close() ;   // 关闭输出流
        System.out.println("unzip done.") ;
    }  
}
```

#### 多个

```java
import java.io.File ;  
import java.io.OutputStream ;  
import java.io.InputStream ;  
import java.util.zip.ZipEntry ;  
import java.util.zip.ZipFile ;  
import java.util.zip.ZipInputStream ;  
import java.io.FileInputStream ;  
import java.io.FileOutputStream ;  
public class MultipleFileUnzip{  
    public static void main(String args[]) throws Exception{    
       ////待解压的zip文件，需要从zip文件构建输入流，读取数据到java中
        File file = new File("c:/temp/multiple.zip") ;   // 定义压缩文件名称
        File outFile = null ;   // 输出文件的时候要有文件夹的操作
        ZipFile zipFile = new ZipFile(file) ;   // 实例化ZipFile对象  
        ZipInputStream zipInput = null ;    // 定义压缩输入流 
        
        //定义解压的文件名
        OutputStream out = null ;   // 定义输出流，用于输出每一个实体的内容  
        InputStream input = null ;  // 定义输入流，读取每一个ZipEntry  
        ZipEntry entry = null ; // 每一个压缩实体  
        zipInput = new ZipInputStream(new FileInputStream(file)) ;  // 实例化ZIpInputStream 
        
        //遍历压缩包中的文件
        while((entry = zipInput.getNextEntry())!=null){ // 得到一个压缩实体  
           System.out.println("��ѹ��" + entry.getName() + "�ļ�") ;  
            outFile = new File("c:/temp/" + entry.getName()) ;   // 定义输出的文件路径  
            if(!outFile.getParentFile().exists()){  // 如果输出文件夹不存在 
                outFile.getParentFile().mkdir() ;   
                // 创建文件夹，多级文件夹不存在，用mkdirs()
                // 一级文件夹用mkdir()
            }  
            if(!outFile.exists()){  // 判断输出文件是否存在
               if(entry.isDirectory())
               {
                  outFile.mkdirs();
                  System.out.println("create directory...");
               }
               else
               {
                  outFile.createNewFile() ;   // 创建文件
                  System.out.println("create file...");
               }                  
            }  
            if(!entry.isDirectory())
            {
               input = zipFile.getInputStream(entry) ; // 得到每一个实体的输入流 
                out = new FileOutputStream(outFile) ;   // 实例化文件输出流  
                int temp = 0 ;  
                while((temp=input.read())!=-1){  
                    out.write(temp) ;  
                }  
                input.close() ;     // 关闭输入流  
                out.close() ;   // 关闭输出流  
            }
            
        }  
        input.close() ;  
    }  
}
```
