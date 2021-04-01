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

        News news1=new News();
        News news2=new News();
        News news3=new News();
        News news4=new News();
        @Before
        public void setup(){

            news1.setAuthor("A1");
            news1.setCopyright("cp1");

            news2.setAuthor("A2");
            news2.setCopyright("cp2");

            news3.setAuthor("A3");
            news3.setCopyright("cp3");

            news4.setAuthor("A4");
            news4.setCopyright("cp4");

        }
        @Test
        public void getAllNewsBySizeTest() {
            LOGGER.debug("NewsControllerTest.getAllNewsBySizeTest");
            newsService.saveAll(Arrays.asList(news1,news2));
            ResponseEntity<List<News>> newsList = newsController.getNews(10);
            Assert.assertEquals( HttpStatus.OK,newsList.getStatusCode());
            Assert.assertNotNull(newsList.getBody());
            Assert.assertEquals(2,newsList.getBody().size());
            Assert.assertFalse(newsList.getBody().contains(news3));
            newsService.saveAll(Arrays.asList(news3,news4));
            newsList = newsController.getNews(4);
            Assert.assertEquals( HttpStatus.OK,newsList.getStatusCode());
            Assert.assertNotNull(newsList.getBody());
            Assert.assertEquals(4, newsList.getBody().size());
            Assert.assertTrue(newsList.getBody().contains(news1));

        }


    }