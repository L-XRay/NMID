import com.test.pojo.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args){
        //获取Spring的上下文对象
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        //对象都在Spring里管理，直接从里面取出对象使用
        Hello hello=(Hello) context.getBean("hello");
        System.out.println(hello);
    }
}
