package cn.kiwipeach.blog.service;

import cn.kiwipeach.blog.BlogWebApplicationTests;
import cn.kiwipeach.blog.domain.Blog;
import com.baomidou.mybatisplus.plugins.Page;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

public class IDemoServiceTest extends BlogWebApplicationTests {

    @Autowired
    private IDemoService idemoService;

    @Test
    @Rollback(value = false)
    public void 测试业务逻辑层的事务一致性() {
        Blog blog = new Blog();
        blog.setId("100");
        blog.setTitle("50岁的程序员该何去何从-77777");
        idemoService.testTranactional(blog);
    }

    @Test
    public void 测试业务逻辑层的简单查询(){
        idemoService.selectById("100");
    }

    @Test
    public void 测试业务逻辑层的默认分页查询(){
        Page<Blog> page = new Page<>();
        Page<Blog> blogIPage = idemoService.selectPage(page,null);
        System.out.println(blogIPage);
    }

    @Test
    public void 测试业务逻辑层的自定义分页查询(){
        Page<Blog> page = new Page<>(0,3);
        Page<Blog> blogIPage = idemoService.selectBlogPage(page, "10086");
        System.out.println(blogIPage);
    }
}