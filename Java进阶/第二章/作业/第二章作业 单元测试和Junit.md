### 第二章作业 单元测试和Junit

#### DateUtil

```java
public class DateUtil {
    public  boolean isLeapYear(int year) {
        if(year>0&&year<10000&&year % 4 == 0 && year % 100 != 0 || year % 400 == 0&&year%100==0){
            return true;
        }
        else{
            return false;
        }
    }
}
```

#### DateUtilTest

```java
import static org.junit.Assert.*;

import org.junit.Test;

public class DateUtilTest {
    @Test
    public void test() {
        assertEquals(false, new DateUtil().isLeapYear(-100));
        assertEquals(false, new DateUtil().isLeapYear(1000));
        assertEquals(true, new DateUtil().isLeapYear(20000));
        assertEquals(true, new DateUtil().isLeapYear(2020));
        assertEquals(false, new DateUtil().isLeapYear(2019));
        assertEquals(true, new DateUtil().isLeapYear(2000));
        assertEquals(false, new DateUtil().isLeapYear(1900));
    }
}
```

**单个测试**

![](%E7%AC%AC%E4%BA%8C%E7%AB%A0%E4%BD%9C%E4%B8%9A%20%E5%8D%95%E5%85%83%E6%B5%8B%E8%AF%95%E5%92%8CJunit.assets/%E5%8D%95%E4%B8%AA%E6%B5%8B%E8%AF%95.JPG)

**全部测试**

![](%E7%AC%AC%E4%BA%8C%E7%AB%A0%E4%BD%9C%E4%B8%9A%20%E5%8D%95%E5%85%83%E6%B5%8B%E8%AF%95%E5%92%8CJunit.assets/%E5%85%A8%E9%83%A8%E6%B5%8B%E8%AF%95.JPG)

