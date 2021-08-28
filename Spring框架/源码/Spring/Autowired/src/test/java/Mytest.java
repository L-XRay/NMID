import com.test.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Mytest {
    @Test
    public void test(){
        ApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
        User user=context.getBean("user",User.class);
        user.getCat().shout();
        user.getDog().shout();
        user.getCat().shout();
    }
}
