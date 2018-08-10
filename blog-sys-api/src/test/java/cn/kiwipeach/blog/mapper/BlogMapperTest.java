package cn.kiwipeach.blog.mapper;

import cn.kiwipeach.blog.BlogApiApplicationTests;
import cn.kiwipeach.blog.domain.Blog;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javafx.scene.control.Pagination;
import org.apache.catalina.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BlogMapperTest extends BlogApiApplicationTests {

    @Autowired
    private BlogMapper blogMapper;

    @Test
    public void selectById() {
        Blog blog = blogMapper.selectById("100");
        System.out.println(blog);
    }

    @Test
    public void selectList() {
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<Blog>().eq("user_id", "10086");
        blogMapper.selectList(queryWrapper);
    }

    @Test
    public void pageQuery() {
        //IPage<Blog> page = new Page<>(1, 5);
        //QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        //queryWrapper.eq("user_id", "10086");
        //IPage<Blog> blogIPage = blogMapper.selectPage(page, queryWrapper);
        //System.out.println("总记录:" + blogIPage.getTotal());
        String userId = "10086";
        //Pagination page = new Pagination();
        Page<Blog> page = new Page<>(1,5);
        List<Blog> blogs = blogMapper.selectBlogList(page, userId);
        System.out.println("总记录:" + blogs.size());
    }

}