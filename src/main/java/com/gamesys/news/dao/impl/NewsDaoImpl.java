package com.gamesys.news.dao.impl;

import com.gamesys.news.dao.NewsDao;
import com.gamesys.news.dao.mapper.NewsMapper;
import com.gamesys.news.domain.News;
import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.googlecode.ehcache.annotations.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Raminda
 * @apiNote dao impl for news
 */
@Repository
public class NewsDaoImpl implements NewsDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewsDaoImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    static final String INSERT_SQL = "INSERT INTO news  " +
            "(news_title,news_link,news_description," +
            "news_language,news_copyright,news_pub_date,news_author," +
            "news_icon,news_image,news_docs,news_created_at) VALUES (?,?,?,?,?,?,?,?,?,?,?) ";
    static final String SELECT_SQL = "SELECT * FROM news";
    static final String SELECT_TOP_SQL = "SELECT Top ? * FROM news ORDER BY news_pub_date DESC";

    /**
     * @apiNote return all
     * @return
     */
    @Override
    public List<News> findAll() {
        return jdbcTemplate.queryForStream(
                SELECT_SQL,new NewsMapper()).collect(Collectors.toList());
    }

    /**
     * @apiNote dft value for size is 10
     * @param size
     * @return List<News>
     */
    @Override
    @Cacheable(cacheName = "find_all_news")
    public List<News> findAll(Integer size) {
        return jdbcTemplate.queryForStream(
                SELECT_TOP_SQL,getPreparedStatementSetter(Arrays.asList(size)),new NewsMapper()).collect(Collectors.toList());
    }

    /**
     *
     * @param news
     * @return id
     */
    @Override
    @TriggersRemove(cacheName = "find_all_news", when = When.AFTER_METHOD_INVOCATION,removeAll = true)
    public int save(News news) {
        return jdbcTemplate.update(INSERT_SQL,
                getPreparedStatementSetter(Arrays.asList(news
                .getAsArray())));
    }

    /**
     *
     * @param news
     * @return ids[]
     */
    @Override
    @TriggersRemove(cacheName = "find_all_news", when = When.AFTER_METHOD_INVOCATION,removeAll = true)
    public int[] saveAll(List<News> news) {
        return jdbcTemplate.batchUpdate(INSERT_SQL,getBatchPreparedStatementSetter(news));
    }

    /**
     * @apiNote create news
     * @param params
     * @return
     */
    private PreparedStatementSetter getPreparedStatementSetter(final List<Object> params) {
        return new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                if(!params.isEmpty()) {
                    Integer count = 1;
                    for (Object o : params) {
                        preparedStatement.setString(count++, o!=null?o.toString():null);
                    }
                }
            }
        };
    }

    /**
     * @apiNote create news list
     * @param newsList
     * @return BatchPreparedStatementSetter
     */
    private BatchPreparedStatementSetter getBatchPreparedStatementSetter(final List<News> newsList) {
        return new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                if(!newsList.get(i).getAsArray().isEmpty()) {
                    List params = newsList.get(i).getAsArray();
                    Integer count = 1;
                    for (Object o : params) {
                        ps.setObject(count++,  o!=null?o.toString():null);
                    }
                }
            }
            @Override
            public int getBatchSize() {
                return newsList.size();
            }
        };
    }


}

