package cn.kiwipeach.blog.mapper;

import cn.kiwipeach.blog.BlogApiApplicationTests;
import cn.kiwipeach.blog.domain.ARBlog;
import cn.kiwipeach.blog.domain.Blog;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONString;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

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
        QueryWrapper<Blog> Wrapper = new QueryWrapper<Blog>().eq("cate_id", "1");
        List<Blog> blogs = demoMapper.selectList(Wrapper);
        log.info("selectList==>{}", blogs);
    }

    /**
     * mybatis-plus提供的分页功能
     */
    @Test
    public void 测试分页selectPage查询() {
        //QueryWrapper<Blog> Wrapper = new QueryWrapper<Blog>().eq("user_id", "10086");
        QueryWrapper<Blog> Wrapper = new QueryWrapper<Blog>().orderByDesc("TOP","UPDATE_TIME");
        // 分页结果中不含有total信息，默认首页为:1
        IPage<Blog> blogPage = new Page<Blog>(1, 5);
        IPage<Blog> blogIPage = demoMapper.selectPage(blogPage, Wrapper);
        log.info("selectPage==>{}", JSON.toJSONString(blogIPage));
    }

    /**
     * 自定义的分页功能
     */
    @Test
    public void 测试selectBlogList分页功能() {
        Page<Blog> page = new Page<>(2, 3);
        List<Blog> blogs = demoMapper.selectBlogListDemo(page, "10086");
        log.info("selectBlogList==>{}", page);
    }

    @Autowired
    private BlogMapper blogMapper;

    @Rollback(value = false)
    @Test
    public void 测试insert插入() {
        Blog blog = new Blog();
        //blog.setId("109950");
        blog.setTitle("kiwipeach是一个程序员");
        blog.setContent("他很帅，很有才华，同时也很烦恼");
        blog.setCateId("1");
        blog.setTop(0);
        blog.setUserId("kiwipeach");
        Integer insert = blogMapper.insert(blog);
        //boolean insert = blog.insert();
        //SysPermission permission = new SysPermission();
        //permission.setIcon("icon-1.png");
        //permission.setWeight(1);
        //permission.setName("博客评论权限");
        //Integer insert = sysPermissionMapper.insert(permission);
        log.info("插入结果：{}", insert);
    }


    /**
     * 二、ActiveRecord基本操作
     */
    @Test
    public void AR操作测试() {
        //查询报错
        ARBlog arBlog = new ARBlog();
        ARBlog arResult = arBlog.selectById("100");
        log.info("查询结果:{}", arResult);

        //查询操作
        ARBlog blog = new ARBlog().selectById("100");
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
        IPage<ARBlog> blogIPage = new ARBlog().selectPage(new Page<ARBlog>(1, 3), null);
        log.info("分页结果:{}", blogIPage);

    }

}