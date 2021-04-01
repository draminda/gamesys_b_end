package com.gamesys.news.config.schedulers;

import com.gamesys.news.config.FeedConfig;
import com.gamesys.news.dao.NewsDao;
import com.gamesys.news.domain.News;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Raminda
 * @apiNote  for getting feed information
 */
@Configuration
@EnableScheduling
public class NewsSchedule {

    private static final Logger LOGGER = LoggerFactory.getLogger(NewsSchedule.class);
    @Autowired
    FeedConfig feedDtl;

    @Autowired
    NewsDao newsDao;

    final int fixedDelay = 60000;
    final int  initialDelay = 1000;

    /**
     * @apiNote Scheduler for feed information
     * @throws IOException
     * @throws FeedException
     */
    @Scheduled(fixedDelay = fixedDelay, initialDelay = initialDelay)
    public void retrieveNewsBySchedule() throws IOException, FeedException {
        LOGGER.debug("NewsSchedule.retrieveNewsBySchedule now {}", Instant.now());
        URL feedSource = new URL(feedDtl.getFeedSource());
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(feedSource));
        List<News> newsList = feed.getEntries().stream().map(this::syndEntryToNews).collect(Collectors.toList());
        List<News> oldRss= newsDao.findAll();
        LOGGER.debug("newsList.size {}", newsList.size());
        if(!newsList.isEmpty()) {
            newsDao.saveAll(oldRss.isEmpty()?
                    newsList:
                    newsList.stream().filter(news -> !newsList.contains(news)).collect(Collectors.toList()));
        }
        LOGGER.debug("feed {}", feed);
    }

    /**
     * @apiNote conver to News
     * @param syndEntry
     * @return
     */
    private News syndEntryToNews(SyndEntry syndEntry){
        News news = new News();;
        if(syndEntry.getSource()!=null) {
            news.setTitle(syndEntry.getSource().getTitle());
            news.setLink(syndEntry.getSource().getLink());
            news.setDescription(syndEntry.getSource().getDescription());
            news.setLanguage(syndEntry.getSource().getLanguage());
            news.setCopyright(syndEntry.getSource().getCopyright());
            news.setPubDate(syndEntry.getSource().getPublishedDate().toInstant());

            news.setAuthor(syndEntry.getSource().getAuthor());
            news.setIcon(syndEntry.getSource().getIcon().getUrl());
            news.setImage(syndEntry.getSource().getImage().getUrl());
            news.setDocs(syndEntry.getSource().getDocs());
        }
        else{
            news.setTitle(syndEntry.getTitle());
            news.setLink(syndEntry.getLink());
            news.setDescription(syndEntry.getDescription()!=null ? syndEntry.getDescription().getValue():null);
            news.setPubDate(syndEntry.getPublishedDate().toInstant());
            news.setAuthor(syndEntry.getAuthor());
            news.setDocs(syndEntry.getComments());
        }
        return news;
    }
}
