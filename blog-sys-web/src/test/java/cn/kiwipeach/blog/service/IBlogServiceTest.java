package cn.kiwipeach.blog.service;

import cn.kiwipeach.blog.BlogWebApplicationTests;
import cn.kiwipeach.blog.domain.Blog;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class IBlogServiceTest extends BlogWebApplicationTests {

    @Autowired
    private IBlogService iBlogService;

    @Test
    public void testTranactional() {
        Blog blog = new Blog();
        blog.setId("100");
        blog.setTitle("50岁的程序员该何去何从-XXXXXX");
        iBlogService.testTranactional(blog);
    }

    @Test
    public void testSelectById(){
        iBlogService.selectById("100");
    }

    @Test
    public void selectPage(){
        Page<Blog> page = new Page<>();
        IPage<Blog> blogIPage = iBlogService.selectPage(page,null);
        System.out.println(blogIPage);
    }

    @Test
    public void Page(){
        Page<Blog> page = new Page<>(0,3);
        IPage<Blog> blogIPage = iBlogService.selectBlogPage(page, "10086");
        System.out.println(blogIPage);
    }
}