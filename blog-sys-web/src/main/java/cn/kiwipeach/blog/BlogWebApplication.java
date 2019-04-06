package cn.kiwipeach.blog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;


@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = {"cn.kiwipeach.blog", "org.kiwipeach.blog"})
@EnableCaching
@Slf4j
//@ImportResource(locations = {"classpath:spring-shiro.xml"})
public class BlogWebApplication extends SpringBootServletInitializer {

    @Value("${blog.shiro.cookie.domain}")
    private String domain;

    @PostConstruct
    public void sysInitBanner() {
        log.warn("系统访问前台[qq,github,gitee]:http://{}", domain);
        //log.info("系统访问后台[qq,github,gitee]:http://www.kiwipeach.cn/amdin/index.html");
        log.warn("系统duird监控[admin/123456]:http://{}/druid", domain);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BlogWebApplication.class);
    }

    public static void main(String[] args) {
//		SpringApplication app = new SpringApplication(BlogWebApplication.class);
//		app.setBannerMode(Banner.Mode.CONSOLE);
//		app.run(args);
        SpringApplication.run(BlogWebApplication.class, args);
    }
}
