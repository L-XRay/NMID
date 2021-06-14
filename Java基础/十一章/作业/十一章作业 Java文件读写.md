### 十一章作业 Java文件读写

```java
package net.word.count;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

class MyComparator implements Comparator<Map.Entry<String,Integer>> {
    public int compare(HashMap.Entry<String,Integer> he1, HashMap.Entry<String,Integer> he2) {
        return he2.getValue()-he1.getValue();
    }
}

public class Main {
    private static void writeFile() {
        //try-resource 语句，自动关闭资源
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\Java\\a.txt")))) {
            bw.write("hello world");
            bw.newLine();
            bw.newLine();
            bw.write("hello");
            bw.newLine();
            bw.newLine();
            bw.write("Java hello world");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static void main(String[] args) {
        HashMap<String, Integer> hm = new HashMap<String,Integer>();
        File f1=new File("D:\\Java\\a.txt"); // 创建文件
        if(!f1.exists())
        {
            try
            {
                f1.createNewFile(); //创建a.txt
                writeFile();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }

        //文件读取
        try(BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("D:\\Java\\a.txt")))){
            String line;
            while((line = br.readLine())!=null) {
                Pattern p = Pattern.compile("\\s+");  //正则匹配空格
                String words[]= p.split(line); //获取的字符串进行分割单词

                for(String item : words) {
                    if(item.length()!=0) {
                        if(hm.get(item)==null)
                            hm.put(item, 1); //将数据放入map中
                        else
                        hm.put(item, hm.get(item)+1);
                    }
                }
            }
        }catch(Exception e) {
            System.err.println(e.getMessage());
        }

        //HashMap转换为list，进行排序
        List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(hm.entrySet());
        Collections.sort(list,new MyComparator());

        File f2=new File("D:\\Java\\b.txt"); // 创建文件
        if(!f2.exists())
        {
            try
            {
                f2.createNewFile(); //创建b.txt
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }

        //将数据写入文件
        try(BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\Java\\b.txt")))){
            for(Map.Entry<String, Integer> item: list) {
                bw.write(item.getKey()+","+item.getValue());
                bw.newLine();
            }
        }catch(Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
```

**运行结果：**

![](%E5%8D%81%E4%B8%80%E7%AB%A0%E4%BD%9C%E4%B8%9A%20Java%E6%96%87%E4%BB%B6%E8%AF%BB%E5%86%99.assets/%E8%BF%90%E8%A1%8C%E7%BB%93%E6%9E%9C.JPG)
