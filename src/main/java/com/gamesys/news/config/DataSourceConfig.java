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
public class DataSourceConfig {
        @Getter
        @Setter
        @Value("${datasource.url}")
        private String url;
        @Getter
        @Setter
        @Value("${datasource.driverClassName}")
        private String driverClassName;
        @Getter
        @Setter
        @Value("${datasource.username}")
        private String username;
        @Getter
        @Setter
        @Value("${datasource.password}")
        private String password;
        @Getter
        @Setter
        @Value("${datasource.platform}")
        private String platform;

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("DataSourceDtl{");
            sb.append("url='").append(url).append('\'');
            sb.append(", driverClassName='").append(driverClassName).append('\'');
            sb.append(", username='").append(username).append('\'');
            sb.append(", password='").append(password).append('\'');
            sb.append(", platform='").append(platform).append('\'');
            sb.append('}');
            return sb.toString();
        }

}
