package cn.kiwipeach.blog;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@EnableTransactionManagement/*启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />*/
@SpringBootTest(classes = {BlogWebApplication.class})
@Transactional
@Rollback(value = true)
public class BlogWebApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext; // 注入WebApplicationContext
    /**
     * 模拟MVC对象
     */
    protected MockMvc mockMvc;


    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }
}
