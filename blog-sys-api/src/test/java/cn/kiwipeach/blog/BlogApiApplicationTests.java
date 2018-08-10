package cn.kiwipeach.blog;

import cn.kiwipeach.blog.domain.Blog;
import cn.kiwipeach.blog.domain.SysUser;
import cn.kiwipeach.blog.mapper.BlogMapper;
import cn.kiwipeach.blog.mapper.SysUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BlogApiApplication.class)
public class BlogApiApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private BlogMapper blogMapper;

    @Test
    public void contextLoads() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    @Test
    public void testUserMapper() {
        Blog blog = blogMapper.selectById("100");
        System.out.println(blog);
    }

}
