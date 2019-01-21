package cn.kiwipeach.blog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = "cn.kiwipeach.blog")
//@ImportResource(locations = {"classpath:spring-shiro.xml"})
public class BlogWebApplication extends SpringBootServletInitializer {

	private static final Logger logger = LoggerFactory.getLogger(BlogWebApplication.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BlogWebApplication.class);
	}

	public static void main(String[] args) {
//		SpringApplication app = new SpringApplication(BlogWebApplication.class);
//		app.setBannerMode(Banner.Mode.CONSOLE);
//		app.run(args);
		SpringApplication.run(BlogWebApplication.class, args);
		logger.info("系统访问后台:http://www.kiwipeach.cn/");
		logger.info("系统duird监控:http://www.kiwipeach.cn/druid");
	}
}
