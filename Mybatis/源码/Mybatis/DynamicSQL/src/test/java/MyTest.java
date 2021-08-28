import com.test.dao.BlogMapper;
import com.test.pojo.Blog;
import com.test.utils.IDUtils;
import com.test.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import javax.xml.transform.Source;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MyTest {
    @Test
    public void addBlogTest() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog = new Blog();
        blog.setId(IDUtils.getID());
        blog.setTitle("Mybatis");
        blog.setAuthor("狂神说");
        blog.setCreateTime(new Date());
        blog.setViews(999);

        mapper.addBlog(blog);

        blog.setId(IDUtils.getID());
        blog.setTitle("Java");
        mapper.addBlog(blog);

        blog.setId(IDUtils.getID());
        blog.setTitle("Spring");
        mapper.addBlog(blog);

        blog.setId(IDUtils.getID());
        blog.setTitle("微服务");
        mapper.addBlog(blog);

        sqlSession.close();
    }

    @Test
    public void SelectBlogByIF(){
        SqlSession session=MybatisUtils.getSqlSession();
        BlogMapper mapper=session.getMapper(BlogMapper.class);
        HashMap map=new HashMap();
        map.put("title","Java");
        //map.put("author","狂神说");
        List<Blog> blogs = mapper.selectBlogByIF(map);
        for (Blog blog:blogs){
            System.out.println(blog);
        }
        session.close();
    }

    @Test
    public void SelectBlogByChoose(){
        SqlSession session=MybatisUtils.getSqlSession();
        BlogMapper mapper=session.getMapper(BlogMapper.class);
        HashMap map=new HashMap();
        map.put("title","Java");
        map.put("views",999);
        List<Blog> blogs = mapper.selectBlogByChoose(map);
        for (Blog blog:blogs){
            System.out.println(blog);
        }
        session.close();
    }

    @Test
    public void SelectBlogByForeach(){
        SqlSession session=MybatisUtils.getSqlSession();
        BlogMapper mapper=session.getMapper(BlogMapper.class);
        HashMap map=new HashMap();
        ArrayList<Integer> ids=new ArrayList<Integer>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        map.put("ids",ids);

        List<Blog> blogs = mapper.selectBlogByForeach(map);
        for (Blog blog:blogs){
            System.out.println(blog);
        }
        session.close();
    }

    @Test
    public void UpdateBlog(){
        SqlSession session=MybatisUtils.getSqlSession();
        BlogMapper mapper=session.getMapper(BlogMapper.class);
        HashMap map=new HashMap();
        //map.put("id","1");
        map.put("title","Java");
        map.put("author","瑞");
        int res= mapper.updateBlog(map);
        if(res!=0){
            System.out.println("更新成功");
            System.out.println(map);
        }
        session.close();
    }
}
