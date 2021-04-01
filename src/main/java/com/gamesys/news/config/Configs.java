package com.gamesys.news.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author raminda
 * @apiNote  general configs
 */
@Configuration
@EnableScheduling
@ComponentScan("com.gamesys.news")
public class Configs {
}
