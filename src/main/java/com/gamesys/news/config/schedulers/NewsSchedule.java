package com.gamesys.news.config.schedulers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class NewsSchedule {

    private static final Logger LOGGER = LoggerFactory.getLogger(NewsSchedule.class);

    @Scheduled(fixedDelay = 2000, initialDelay = 1000)
    public void retrieveNewsBySchedule() {
        long now = System.currentTimeMillis() / 1000;
        LOGGER.debug(" no is : {}",now);
    }
}
