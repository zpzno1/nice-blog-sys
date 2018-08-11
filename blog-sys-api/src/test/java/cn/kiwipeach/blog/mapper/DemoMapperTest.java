package cn.kiwipeach.blog.mapper;

import cn.kiwipeach.blog.BlogApiApplicationTests;
import cn.kiwipeach.blog.domain.ARBlog;
import cn.kiwipeach.blog.domain.Blog;
import cn.kiwipeach.blog.domain.SysPermission;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
public class DemoMapperTest extends BlogApiApplicationTests {

    @Autowired
    private DemoMapper demoMapper;

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    /**
     * 一、BaseMapper中的基本操作
     */
    @Test
    public void 测试selectById查询() {
        Blog blog = demoMapper.selectById("100");
        log.info("selectById==>{}", blog);
    }

    @Test
    public void 测试selectList查询() {
        Wrapper<Blog> Wrapper = new EntityWrapper<Blog>().eq("cate_id", "1");
        List<Blog> blogs = demoMapper.selectList(Wrapper);
        log.info("selectList==>{}", blogs);
    }

    @Test
    public void 测试分页selectPage查询() {
        Wrapper<Blog> Wrapper = new EntityWrapper<Blog>().eq("user_id", "10086");
        // 分页结果中不含有total信息，默认首页为:1
        Page<Object> blogPage = new Page<>(2, 3);
        List<Blog> blogs = demoMapper.selectPage(blogPage, Wrapper);
        log.info("selectPage==>{}", blogs);
    }

    @Rollback(value = false)
    @Test
    public void 测试insert插入() {
        //Blog blog = new Blog();
        //blog.setTitle("kiwipeach是一个程序员");
        //blog.setContent("他很帅，很有才华，同时也很烦恼");
        //blog.setCateId("1");
        //blog.setTop(new BigDecimal(1));
        //boolean insert = blog.insert();
        SysPermission permission = new SysPermission();
        permission.setIcon("icon-1.png");
        permission.setWeight(new BigDecimal(1));
        permission.setName("博客评论权限");
        Integer insert = sysPermissionMapper.insert(permission);
        log.info("插入结果：{}", insert);
    }


    /**
     * 二、ActiveRecord基本操作
     */
    @Test
    public void AR操作测试() {
        //查询报错
        //ARBlog arBlog = new ARBlog();
        //arBlog.selectById("100");
        //System.out.println(arBlog);

        //查询操作
        Blog blog = new Blog().selectById("100");
        log.info("查询结果:{}", blog);

        //更新操作
        blog.setTitle(blog.getTitle() + "y");
        boolean updateFlag = blog.updateById();
        log.info("更新结果:{}", updateFlag);
        System.out.println(updateFlag);

        //删除操作
        boolean deleteFlag = blog.deleteById();
        log.info("删除结果:{}", deleteFlag);

        //分页操作
        Page<Blog> blogPage = new Blog().selectPage(new Page<Blog>(1, 3), null);
        log.info("分页结果:{}", blogPage);

    }

}