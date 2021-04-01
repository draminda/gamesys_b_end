package com.gamesys.news.controller;

import com.gamesys.news.domain.News;
import com.gamesys.news.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Raminda
 * @apiNote Main REST api
 */
@RestController
@RequestMapping("/api")
@Component
public class NewsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    private NewsService newsService;

    /**
     * @apiNote Get News
     * @param pageSize
     */
    @GetMapping("/news")
    public ResponseEntity<List<News>> getNews(final @RequestParam(name = "size",required = false)Integer pageSize) {
        LOGGER.debug("NewsController.getNews start size : {}",pageSize);
        return new ResponseEntity(newsService.getAllNews(pageSize), HttpStatus.OK) ;
    }
}
