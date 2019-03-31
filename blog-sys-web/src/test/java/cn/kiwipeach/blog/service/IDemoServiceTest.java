package cn.kiwipeach.blog.service;

import cn.kiwipeach.blog.BlogWebApplicationTests;
import cn.kiwipeach.blog.domain.Blog;
import cn.kiwipeach.blog.mapper.DemoMapper;
import cn.kiwipeach.blog.service.demo.IDemoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@Slf4j
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
    public void 测试业务逻辑层的简单查询() {
        idemoService.getById("100");
    }

    @Test
    public void 测试业务逻辑层的默认分页查询() {
        Page<Blog> page = new Page<>();
        IPage<Blog> blogIPage = idemoService.page(page, null);
        System.out.println(blogIPage);
    }

    @Test
    public void 测试业务逻辑层的自定义分页查询() {
        Page<Blog> page = new Page<>(1, 3);
        IPage<Blog> blogIPage = idemoService.queryBlogListDemo(page, "10086");
        System.out.println(blogIPage);
    }

    @Autowired
    private DemoMapper demoMapper;

    @Test
    public void 测试在Service层直接调用分页() {
        Page<Blog> page = new Page<>(1, 3);
        List<Blog> blogs = demoMapper.selectBlogListDemo(page, null);
        log.info("结果:{}", blogs);
    }
}