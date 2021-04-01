package com.gamesys.news.dao;

import com.gamesys.news.domain.News;

import java.util.List;

/**
 * @author  Raminda
 */
public interface NewsDao {
    List<News> findAll();

    List<News> findAll(Integer size);

    int save(News news);

    int[] saveAll(List<News> news);
}
