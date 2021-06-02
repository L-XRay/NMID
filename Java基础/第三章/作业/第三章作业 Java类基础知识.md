### 第三章作业 Java类基础知识

```java
package main;

import java.util.*;

public class num_square {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n;
        System.out.println("请输入行列数:");
        n=s.nextInt();
        for(int i=1;i<=n*n;i++){
            System.out.print(i+" ");
            if(i%n==0){
                System.out.println();
            }
        }
    }
}


```

**运行结果:**

![](%E7%AC%AC%E4%B8%89%E7%AB%A0%E4%BD%9C%E4%B8%9A%20Java%E7%B1%BB%E5%9F%BA%E7%A1%80%E7%9F%A5%E8%AF%86.assets/%E4%BD%9C%E4%B8%9A1.JPG)