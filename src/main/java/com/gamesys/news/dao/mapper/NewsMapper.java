package com.gamesys.news.dao.mapper;

import com.gamesys.news.domain.News;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author  Raminda
 * @apiNote News Data row convertor
 */
public class NewsMapper implements RowMapper<News> {

    /**
     *
     * @param rs ResultSet
     * @param rowNum row number
     * @return News
     * @throws SQLException if data set not rendered
     */
    @Override
    public News mapRow(ResultSet rs, int rowNum) throws SQLException {
        News news = new News();
        news.setId(rs.getLong("id"));
        news.setCopyright(rs.getString("news_copyright"));
        news.setDescription(rs.getString("news_description"));
        news.setLanguage(rs.getString("news_language"));
        news.setPubDate(rs.getTimestamp("news_pub_date")!=null?
                rs.getTimestamp("news_pub_date").toInstant():null);
        news.setLink(rs.getString("news_link"));
        news.setTitle(rs.getString("news_title"));
        news.setAuthor(rs.getString("news_author"));
        news.setIcon(rs.getString("news_icon"));
        news.setImage(rs.getString("news_image"));
        news.setDocs(rs.getString("news_docs"));
        news.setCreatedAt(rs.getTimestamp("news_created_at")!=null?
                rs.getTimestamp("news_created_at").toInstant():null);
        return news;
    }
}