### 第七章作业 package、import和classpath

[TOC]

#### jar文件导出导入

```java
package com.test;

public class Worker {
    public void sayHello()
    {
        System.out.println("hello");
    }
}
```

```Java
package com.bussiness;

import com.test.Worker;

class Boss
{
    public static void main(String[] a)
    {
        Worker w = new Worker();
        w.sayHello();
    }
}
```

**导出：**

![](%E7%AC%AC%E4%B8%83%E7%AB%A0%E4%BD%9C%E4%B8%9A%20package%E3%80%81import%E5%92%8Cclasspath.assets/%E5%AF%BC%E5%87%BA.JPG)

**导入：**

![](%E7%AC%AC%E4%B8%83%E7%AB%A0%E4%BD%9C%E4%B8%9A%20package%E3%80%81import%E5%92%8Cclasspath.assets/%E5%AF%BC%E5%85%A5.JPG)

**运行结果：**

![](%E7%AC%AC%E4%B8%83%E7%AB%A0%E4%BD%9C%E4%B8%9A%20package%E3%80%81import%E5%92%8Cclasspath.assets/%E8%BF%90%E8%A1%8C%E7%BB%93%E6%9E%9C.JPG)

#### 命令行中的java编译运行

![](%E7%AC%AC%E4%B8%83%E7%AB%A0%E4%BD%9C%E4%B8%9A%20package%E3%80%81import%E5%92%8Cclasspath.assets/%E4%BD%9C%E4%B8%9A2.JPG)

##### C编译运行

![](%E7%AC%AC%E4%B8%83%E7%AB%A0%E4%BD%9C%E4%B8%9A%20package%E3%80%81import%E5%92%8Cclasspath.assets/%E4%BD%9C%E4%B8%9A2.1.JPG)

##### F编译运行

![](%E7%AC%AC%E4%B8%83%E7%AB%A0%E4%BD%9C%E4%B8%9A%20package%E3%80%81import%E5%92%8Cclasspath.assets/%E4%BD%9C%E4%B8%9A2.2.JPG)

##### H编译运行

![](%E7%AC%AC%E4%B8%83%E7%AB%A0%E4%BD%9C%E4%B8%9A%20package%E3%80%81import%E5%92%8Cclasspath.assets/%E4%BD%9C%E4%B8%9A2.3.JPG)
