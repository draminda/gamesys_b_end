package com.gamesys.news.config;

import com.gamesys.news.config.dtl.ConfigDtl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

/**
 * @author  Raminda
 * @apiNote Db configs
 */
@Configuration
public class DbConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(DbConfig.class);

    /**
     * @apiNote Bean for initialising data Source
     * @return DataSource
     */
    @Bean
    public DataSource db2DataSource(ConfigDtl.DataSourceConfig dataSourceDtl) {
        LOGGER.debug("DbConfig.db2DataSource start");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dataSourceDtl.getDriverClassName());
        dataSource.setUrl(dataSourceDtl.getUrl());
        dataSource.setUsername(dataSourceDtl.getUserName());
        dataSource.setPassword(dataSourceDtl.getPassword());
        LOGGER.debug("DbConfig.db2DataSource end");
        return dataSource;
    }

    /**
     * @apiNote Bean for initialising data table
     * @return DataSourceInitializer
     */
    @Bean
    public DataSourceInitializer dataSourceInitializer(ConfigDtl.DataSourceConfig dataSourceDtl) {
        LOGGER.debug("DbConfig.dataSourceInitializer start");
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("/news.sql"));

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(db2DataSource(dataSourceDtl));

        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        LOGGER.debug("DbConfig.dataSourceInitializer end");
        return dataSourceInitializer;
    }


}
