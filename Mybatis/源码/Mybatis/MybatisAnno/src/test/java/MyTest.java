import com.test.dao.UserMapper;
import com.test.pojo.User;
import com.test.utils.MybatisUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import javax.xml.transform.Source;
import java.util.List;

public class MyTest {
    @Test
    public void AllSelect(){
        SqlSession session= MybatisUtils.getSqlSession();
        UserMapper mapper=session.getMapper(UserMapper.class);
        List<User> userList=mapper.getUsers();
        for (User user : userList) {
            System.out.println(user);
        }
        session.close();
    }

    @Test
    public void SelectByID(){
        SqlSession session= MybatisUtils.getSqlSession();
        UserMapper mapper=session.getMapper(UserMapper.class);
        User user=mapper.getUserByID(2);
        System.out.println(user);
        session.close();
    }

    @Test
    public void AddUser(){
        SqlSession session= MybatisUtils.getSqlSession();
        UserMapper mapper=session.getMapper(UserMapper.class);
        int res=mapper.addUser(new User(5,"瑞","33657"));
        if(res!=0)
        System.out.println("添加成功！");
        session.close();
    }

    @Test
    public void UpdateUser(){
        SqlSession session= MybatisUtils.getSqlSession();
        UserMapper mapper=session.getMapper(UserMapper.class);
        int res=mapper.updateUser(new User(5,"wuy","12345"));
        if(res!=0)
            System.out.println("修改成功！");
        session.close();
    }

    @Test
    public void DeleteUser(){
        SqlSession session= MybatisUtils.getSqlSession();
        UserMapper mapper=session.getMapper(UserMapper.class);
        int res=mapper.deleteUser(5);
        if(res!=0)
            System.out.println("删除成功！");
        session.close();
    }
}
