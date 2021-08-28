import com.test.dao.UserDaoImpl;
import com.test.dao.UserDaoMySQLImpl;
import com.test.dao.UserDaoOracleImpl;
import com.test.dao.UserDaoSQLServiceImpl;
import com.test.service.UserService;
import com.test.service.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args){
     /*   //用户实际调用的是业务层，不需要接触Dao层
        UserService userService=new UserServiceImpl();
        userService.GetUser();*/

        //获取ApplicationContext；获取Spring容器
        ApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
        UserServiceImpl UserServiceImpl=(UserServiceImpl) context.getBean("UserServiceImpl");

        UserServiceImpl.GetUser();
    }
}
