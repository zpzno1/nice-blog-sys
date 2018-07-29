package cn.kiwipeach.blog;

import cn.kiwipeach.blog.domain.User;
import cn.kiwipeach.blog.mapper.UserMapper;
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
	private UserMapper userMapper;

	@Test
	public void contextLoads() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    @Test
	public void testUserMapper(){
        User user = userMapper.selectById("1099501218");
        System.out.println(user);
    }

}
