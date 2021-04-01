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

    static final String COLUMNS = "news_title,news_link,news_description," +
            "news_language,news_copyright,news_pub_date,news_author," +
            "news_icon,news_image,news_docs,news_created_at";
    static final String INSERT_SQL = "INSERT INTO news  ("
            + COLUMNS +") VALUES (?,?,?,?,?,?,?,?,?,?,?) ";
    static final String SELECT_SQL = "SELECT id,"+COLUMNS+
            " FROM news";
    static final String DELETE_ALL_SQL = "DELETE  FROM news";
    static final String DELETE_SQL = "DELETE  FROM news WHERE id=?";
    static final String SELECT_TOP_SQL = "SELECT Top ? id ," + COLUMNS +
            " FROM news ORDER BY news_pub_date DESC";

    /**
     * @apiNote return all
     * @return List<News>
     */
    @Override
    public List<News> findAll() {
        LOGGER.debug("NewsDaoImpl.findAll");
        return jdbcTemplate.queryForStream(
                SELECT_SQL,new NewsMapper()).collect(Collectors.toList());
    }

    /**
     * @apiNote dft value for size is 10
     * @param size number of records
     * @return List<News>
     */
    @Override
    @Cacheable(cacheName = "find_all_news")
    public List<News> findAll(Integer size) {
        LOGGER.debug("NewsDaoImpl.findAll size {}",size);
        return jdbcTemplate.queryForStream(
                SELECT_TOP_SQL,getPreparedStatementSetter(Arrays.asList(size)),new NewsMapper()).collect(Collectors.toList());
    }

    /**
     *
     * @param news object for save
     * @return id
     */
    @Override
    @TriggersRemove(cacheName = "find_all_news", when = When.AFTER_METHOD_INVOCATION,removeAll = true)
    public int save(News news) {
        LOGGER.debug("NewsDaoImpl.save {}",news);
        return jdbcTemplate.update(INSERT_SQL,
                getPreparedStatementSetter(news
                .getAsArray()));
    }

    /**
     *
     * @param news objects for save
     * @return ids[]
     */
    @Override
    @TriggersRemove(cacheName = "find_all_news", when = When.AFTER_METHOD_INVOCATION,removeAll = true)
    public int[] saveAll(List<News> news) {
        LOGGER.debug("NewsDaoImpl.saveAll {} ",news.size());
        return jdbcTemplate.batchUpdate(INSERT_SQL,getBatchPreparedStatementSetter(news));
    }

    /**
     * @apiNote  delete All
     */
    @Override
    @TriggersRemove(cacheName = "find_all_news", when = When.AFTER_METHOD_INVOCATION,removeAll = true)
    public void deleteAll() {
        LOGGER.debug("NewsDaoImpl.deleteAll");
         jdbcTemplate.execute(DELETE_ALL_SQL);
    }

    /**
     *  @apiNote  delete one by id
     * @param id deleted id
     */
    @Override
    @TriggersRemove(cacheName = "find_all_news", when = When.AFTER_METHOD_INVOCATION,removeAll = true)
    public void delete(Long id) {
        LOGGER.debug("NewsDaoImpl.delete id : {}",id);
        jdbcTemplate.update(DELETE_SQL,getPreparedStatementSetter(Arrays.asList(id)));
    }
    /**
     * @apiNote create news
     * @param params sql injected parms
     * @return PreparedStatementSetter
     */
    private PreparedStatementSetter getPreparedStatementSetter(final List<Object> params) {
        return preparedStatement -> {
            if(!params.isEmpty()) {
                int count = 1;
                for (Object o : params) {
                    preparedStatement.setObject(count++, o);
                }
            }
        };
    }

    /**
     * @apiNote create news list
     * @param newsList News list for generated as params list
     * @return BatchPreparedStatementSetter
     */
    private BatchPreparedStatementSetter getBatchPreparedStatementSetter(final List<News> newsList) {
        return new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                if(!newsList.get(i).getAsArray().isEmpty()) {
                    List<Object> params = newsList.get(i).getAsArray();
                    int count = 1;
                    for (Object o : params) {
                        ps.setObject(count++, o);
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

