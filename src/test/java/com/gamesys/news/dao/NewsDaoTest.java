package com.gamesys.news.dao;

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
public class NewsDaoTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewsDaoTest.class);
    @Autowired
    NewsDao newsDao;
    News news=new News();
    News news0=new News();
    News news1=new News();
    News news2 = new News();
    News news3 = new News();
    @Before
    public void setup(){
         news1=new News();
        news1.setAuthor("A1");
         news2=new News();
        news2.setAuthor("A2");
         news3=new News();
        news3.setAuthor("A3");
        news.setAuthor("ALl");
        news0.setAuthor("All0");
        news3.setAuthor("All4");

    }

    @Test
    public void getAllNewsTest() {
        LOGGER.debug("NewsDaoTest.getAllNewsTest");
        newsDao.deleteAll();
        List<News> newsList= newsDao.findAll();
        Assert.assertTrue(newsList.isEmpty());
        newsDao.save(news);
        newsList= newsDao.findAll();
        Assert.assertEquals(1,newsList.size());
    }

    @Test
    public void getAllNewsBySizeTest(){
        LOGGER.debug("NewsDaoTest.getAllNewsBySizeTest");
        newsDao.deleteAll();
        List<News> newsList= newsDao.findAll();
        Assert.assertEquals(0,newsList.size());
        newsDao.save(news);
        newsList= newsDao.findAll(2);
        Assert.assertEquals(1,newsList.size());
        newsDao.save(news3);
        newsDao.saveAll(Arrays.asList(news1,news2));
        newsList= newsDao.findAll(3);
        Assert.assertEquals(3,newsList.size());
        newsList= newsDao.findAll(1);
        Assert.assertEquals(1,newsList.size());
    }

    @Test
    public void saveTest() {
        LOGGER.debug("NewsDaoTest.saveTest");
        newsDao.deleteAll();
        List<News> newsList= newsDao.findAll();
        Assert.assertEquals(0,newsList.size());
        newsDao.save(news);
        newsList= newsDao.findAll(3);
        Assert.assertEquals(1,newsList.size());
    }

    @Test
    public void saveAllTest() {
        LOGGER.debug("NewsDaoTest.saveAllTest");
        newsDao.deleteAll();
        List<News> newsList= newsDao.findAll();
        Assert.assertEquals(0,newsList.size());
        newsDao.saveAll(Arrays.asList(news,news0));
        newsList= newsDao.findAll(2);
        Assert.assertEquals(2,newsList.size());
        newsDao.saveAll(Arrays.asList(news1,news2));
        newsList= newsDao.findAll(3);
        Assert.assertEquals(3,newsList.size());
    }

    @Test
    public void DeleteTest() {
        LOGGER.debug("NewsDaoTest.saveTest");
        newsDao.deleteAll();
        List<News> newsList= newsDao.findAll();
        Assert.assertEquals(0,newsList.size());
        newsDao.saveAll(Arrays.asList(news1,news2,news,news3));
        newsList= newsDao.findAll();
        newsDao.delete(newsList.get(0).getId());
        newsList= newsDao.findAll();
        Assert.assertEquals(3,newsList.size());
        newsDao.delete(newsList.get(0).getId());
        newsList= newsDao.findAll();
        Assert.assertEquals(2,newsList.size());
    }

    @Test
    public void deleteAllTest() {
        LOGGER.debug("NewsDaoTest.saveAllTest");
        newsDao.deleteAll();
        List<News> newsList= newsDao.findAll();
        Assert.assertEquals(0,newsList.size());
        newsDao.saveAll(Arrays.asList(news1,news2,news,news3));
        newsList= newsDao.findAll();
        Assert.assertEquals(4,newsList.size());
        newsDao.deleteAll();
        newsList= newsDao.findAll();
        Assert.assertTrue(newsList.isEmpty());
    }
}
