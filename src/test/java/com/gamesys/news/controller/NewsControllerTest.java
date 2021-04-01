package com.gamesys.news.controller;

import com.gamesys.news.domain.News;
import com.gamesys.news.service.NewsService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsControllerTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewsControllerTest.class);
        @Autowired
        NewsService newsService;
        @Autowired
        NewsController newsController;


        @Before
        public void setup(){
            News news1=new News();
            news1.setAuthor("A1");
            news1.setCopyright("cp1");
            News news2=new News();
            news2.setAuthor("A2");
            news2.setCopyright("cp2");
            newsService.saveAll(Arrays.asList(news1,news2));
        }
        @Test
        public void getAllNewsBySizeTest() {
            LOGGER.debug("NewsControllerTest.getAllNewsBySizeTest");
            News news1=new News();
            news1.setAuthor("A3");
            news1.setCopyright("cp3");
            News news2=new News();
            news2.setAuthor("A4");
            news2.setCopyright("cp4");
            ResponseEntity<List<News>> newsList = newsController.getNews(10);
            Assert.assertEquals( HttpStatus.OK,newsList.getStatusCode());
            Assert.assertNotNull(newsList.getBody());
            Assert.assertEquals(2,newsList.getBody().size());
            Assert.assertFalse(newsList.getBody().contains(news1));
            newsService.saveAll(Arrays.asList(news1,news2));
            newsList = newsController.getNews(4);
            Assert.assertEquals( HttpStatus.OK,newsList.getStatusCode());
            Assert.assertNotNull(newsList.getBody());
            Assert.assertEquals(4, newsList.getBody().size());
            Assert.assertTrue(newsList.getBody().contains(news1));

        }


    }