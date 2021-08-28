import com.test.dao.UserMapper;
import com.test.pojo.User;
import com.test.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.*;

public class MyTest {
    @Test
    public void getUserListTest(){
        SqlSession session= MybatisUtils.getSqlSession();
        UserMapper userMapper=session.getMapper(UserMapper.class);
        List<User> userList=userMapper.getUserList();
        for (User user : userList) {
            System.out.println(user);
        }
        session.close();
    }

}
