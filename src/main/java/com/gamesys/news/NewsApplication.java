package com.gamesys.news;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "com.gamesys.news")
@EntityScan("com.gamesys.news.domain")
public class NewsApplication  extends SpringBootServletInitializer {
	private static final Logger LOGGER = LoggerFactory.getLogger(NewsApplication.class);
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(NewsApplication.class, args);
		LOGGER.info("NewsApplication is started {}",context.getEnvironment().toString());
	}

}
