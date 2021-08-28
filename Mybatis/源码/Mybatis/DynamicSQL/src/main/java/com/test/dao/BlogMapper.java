package com.test.dao;

import com.test.pojo.Blog;

import java.util.List;
import java.util.Map;

public interface BlogMapper {

    //插入博客
    int addBlog(Blog blog);

    //查询博客
    List<Blog> selectBlogByIF(Map map);
    List<Blog> selectBlogByChoose(Map map);
    List<Blog> selectBlogByForeach(Map map);

    //更新博客
    int updateBlog(Map map);
}
