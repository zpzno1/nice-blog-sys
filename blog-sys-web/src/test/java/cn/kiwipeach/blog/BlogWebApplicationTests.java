package cn.kiwipeach.blog;

import cn.kiwipeach.blog.domain.User;
import cn.kiwipeach.blog.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BlogWebApplication.class})
public class BlogWebApplicationTests {

    @Autowired
    private IUserService iUserService;

    @Test
    public void contextLoads() throws SQLException {
        User user = iUserService.selectById("1099501218");
        System.out.println(user);
    }

}
