### 第一章作业 Maven

- main

  - java

    - pinyin

      ```java
      import net.sourceforge.pinyin4j.*;
      import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
      import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
      import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
      import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
      import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
      import java.util.Scanner;
      
      public class pinyin {
      
          public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination {
              Scanner sc = new Scanner(System.in);
              String chineseString = sc.nextLine();
      
              HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
      
              format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
      
              format.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);
      
              format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
      
              String pinyinString = PinyinHelper.toHanyuPinyinString(chineseString, format," ");
              System.out.println(pinyinString);
          }
          
      }
      ```

- pom.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <project xmlns="http://maven.apache.org/POM/4.0.0"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
      <modelVersion>4.0.0</modelVersion>
  
      <groupId>org.example</groupId>
      <artifactId>Chinese</artifactId>
      <version>1.0-SNAPSHOT</version>
      
  <dependencies>
      <dependency>
          <groupId>com.belerweb</groupId>
          <artifactId>pinyin4j</artifactId>
          <version>2.5.0</version>
      </dependency>
  </dependencies>
  
      <properties>
          <maven.compiler.source>8</maven.compiler.source>
          <maven.compiler.target>8</maven.compiler.target>
      </properties>
  
  </project>
  ```

**Maven编译**

![](%E7%AC%AC%E4%B8%80%E7%AB%A0%E4%BD%9C%E4%B8%9A%20Maven.assets/Maven%E7%BC%96%E8%AF%91.JPG)

**运行结果：**

![](%E7%AC%AC%E4%B8%80%E7%AB%A0%E4%BD%9C%E4%B8%9A%20Maven.assets/%E8%BF%90%E8%A1%8C%E7%BB%93%E6%9E%9C.JPG)

