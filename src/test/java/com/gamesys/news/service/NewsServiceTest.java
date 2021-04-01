package com.gamesys.news.service;

import com.gamesys.news.dao.NewsDao;
import com.gamesys.news.domain.News;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewsServiceTest.class);
    @Autowired
    NewsDao newsDao;
    @Autowired
    NewsService newsService;
    News news=new News();
    News news0=new News();
    @Before
    public void setup(){
        News news1=new News();
        news1.setAuthor("A1");
        News news2=new News();
        news2.setAuthor("A2");
        News news3=new News();
        news3.setAuthor("A3");
        news.setAuthor("ALl");
        news0.setAuthor("All0");
        newsDao.saveAll(Arrays.asList(news1,news2));
    }

    public void getAllNewsTest() {
        LOGGER.debug("NewsServiceTest.getAllNewsTest");
        List<News> newsList= newsService.getAllNews();
        Assert.assertEquals(2,newsList.size());
        newsDao.deleteAll();
        newsList= newsService.getAllNews();
        Assert.assertTrue(newsList.isEmpty());
        newsDao.save(news);
        newsList= newsService.getAllNews();
        Assert.assertEquals(2,newsList.size());
    }


    public void getAllNewsBySize() {
        LOGGER.debug("NewsServiceTest.getAllNewsBySize");
        List<News> newsList= newsService.getAllNews(2);
        Assert.assertEquals(2,newsList.size());
        Assert.assertTrue(newsList.isEmpty());
        newsDao.save(news);
        newsList= newsService.getAllNews(3);
        Assert.assertEquals(3,newsList.size());
        newsList= newsService.getAllNews(2);
        Assert.assertEquals(3,newsList.size());
        newsDao.deleteAll();
        newsList= newsService.getAllNews();
        Assert.assertTrue(newsList.isEmpty());
        newsDao.save(news);
        newsList= newsService.getAllNews(3);
        Assert.assertEquals(3,newsList.size());
    }


    @Test
    public void saveTest() {
        LOGGER.debug("NewsServiceTest.saveTest");
        newsDao.deleteAll();
        List<News> newsList= newsDao.findAll(2);
        Assert.assertTrue(newsList.isEmpty());
        newsService.save(news);
        newsList= newsDao.findAll(2);
        Assert.assertEquals(1,newsList.size());

    }

    @Test
    public void saveAllTest() {
        LOGGER.debug("NewsServiceTest.saveAllTest");
        newsDao.deleteAll();
        List<News> newsList= newsDao.findAll(2);
        Assert.assertTrue(newsList.isEmpty());
        newsService.saveAll(Arrays.asList(news,news0));
        newsList= newsDao.findAll(2);
        Assert.assertEquals(2,newsList.size());
    }
}
