import com.test.service.UserService;
import com.test.service.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("ApplicationContext.xml");
        //动态代理的是接口
        UserService userService=(UserService) context.getBean("userService");
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入要进行的操作:");
        String input=scanner.next();
        switch (input){
            case "增加":userService.insert();break;
            case "删除":userService.delete();break;
            case "修改":userService.update();break;
            case "查询":userService.select();break;
            default:
                System.out.println("不支持的操作！");
        }
    }
}
