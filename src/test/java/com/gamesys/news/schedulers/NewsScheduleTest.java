package com.gamesys.news.schedulers;

import com.gamesys.news.dao.NewsDao;
import com.gamesys.news.domain.News;
import com.rometools.rome.io.FeedException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsScheduleTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(NewsSchedule.class);
    @Autowired
    NewsSchedule newsSchedule;
    @Autowired
    NewsDao newsDao;


    @Test
    public void retrieveNewsByCronScheduleTest() throws IOException, FeedException {
        LOGGER.debug("NewsScheduleTest.retrieveNewsByCronScheduleTest");
        newsDao.deleteAll();
        List<News> newsList = newsDao.findAll();
        Assert.assertTrue(newsList.isEmpty());
        newsSchedule.retrieveNewsByCronSchedule();
        newsList = newsDao.findAll(5);
        Integer firstSize = newsDao.findAll().size();
        Assert.assertEquals(5,newsList.size());
        newsSchedule.retrieveNewsByCronSchedule();
        Integer secondSize = newsDao.findAll().size();
        Assert.assertEquals(firstSize,secondSize);
    }


}
