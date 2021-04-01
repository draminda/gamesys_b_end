package com.gamesys.news.service.impl;

import com.gamesys.news.config.dtl.ConfigDtl;
import com.gamesys.news.dao.NewsDao;
import com.gamesys.news.domain.News;
import com.gamesys.news.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Raminda
 * @apiNote  Service impl for News
 */
@Service
public class NewsServiceImpl implements NewsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NewsServiceImpl.class);

    @Autowired
    private NewsDao newsDao;

    @Autowired
    private ConfigDtl.FeedConfig feedConfig;

    /**
     * @apiNote get all news
     * @return List<News>
     */
    @Override
    public List<News> getAllNews() {
        LOGGER.debug("NewsServiceImpl.getAllNews : start");
        return newsDao.findAll();
    }

    /**
     * @apiNote get limited set by define size dft value is 10
     * @param size number of records needed
     * @return List<News>
     */
    @Override
    public List<News> getAllNews(Integer size) {
        LOGGER.debug("NewsServiceImpl.getAllNews size{} : start",size);
        return newsDao.findAll(size!=null?size:feedConfig.getRetrievalSize());
    }

    /**
     * @apiNote save single news
     * @param news object for save
     * @return int
     */
    @Override
    public int save(News news) {
        return this.newsDao.save(news);
    }

    /**
     * @apiNote save  news list
     * @param news objects for save
     * @return int[]
     */
    @Override
    public int[] saveAll(List<News> news) {
        return this.newsDao.saveAll(news);
    }
}
