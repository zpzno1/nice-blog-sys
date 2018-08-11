package cn.kiwipeach.blog;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@EnableTransactionManagement/*启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />*/
@SpringBootTest(classes = {BlogWebApplication.class})
@Transactional
@Rollback(value = true)
public class BlogWebApplicationTests {
}
