package com.gamesys.news.service;

import com.gamesys.news.domain.News;

import java.util.List;

/**
 * @author Raminda
 * @apiNote  service fro news
 */
public interface NewsService {
    List<News> getAllNews();
    List<News> getAllNews(Integer size);

}
