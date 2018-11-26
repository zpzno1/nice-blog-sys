package cn.kiwipeach.blog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogAdminApplication {
    private static final Logger logger = LoggerFactory.getLogger(BlogAdminApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BlogAdminApplication.class, args);
        logger.info("后台首页:{}","http://localhost:8826/admin/index");
    }
}
