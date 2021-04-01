package com.gamesys.news.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Component
public class NewsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewsController.class);

    @GetMapping("/news")
    public void getNews(final @RequestParam(name = "size",required = false, defaultValue = "10")Integer pageSize) {
        LOGGER.debug("NewsController.getNews start");
        return  ;
    }
}
