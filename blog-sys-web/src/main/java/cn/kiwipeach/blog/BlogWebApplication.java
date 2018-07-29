package cn.kiwipeach.blog;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = "cn.kiwipeach.blog")
public class BlogWebApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BlogWebApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(BlogWebApplication.class);
		app.setBannerMode(Banner.Mode.CONSOLE);
		app.run(args);
		//SpringApplication.run(BlogWebApplication.class, args);
	}
}
