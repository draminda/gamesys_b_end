package com.gamesys.news.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Raminda
 * @apiNote  for getting db related information
 */
@Configuration
@ConfigurationProperties
public class FeedConfig {
        @Getter
        @Setter
        @Value("${feed.feedSource}")
        private String feedSource;


        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("FeedDtl{");
            sb.append("feedSource='").append(feedSource).append('\'');
            sb.append('}');
            return sb.toString();
        }
}
