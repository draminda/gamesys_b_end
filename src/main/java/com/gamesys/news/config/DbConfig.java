package com.gamesys.news.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    DataSourceConfig dataSourceDtl;

    /**
     * @apiNote Bean for initialising data Source
     * @return
     */
    @Bean
    public DataSource db2DataSource() {
        LOGGER.debug("DbConfig.db2DataSource start");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dataSourceDtl.getDriverClassName());
        dataSource.setUrl(dataSourceDtl.getUrl());
        dataSource.setUsername(dataSourceDtl.getUsername());
        dataSource.setPassword(dataSourceDtl.getPassword());
        LOGGER.debug("DbConfig.db2DataSource end");
        return dataSource;
    }

    /**
     * @apiNote Bean for initialising data table
     * @return
     */
    @Bean
    public DataSourceInitializer dataSourceInitializer() {
        LOGGER.debug("DbConfig.dataSourceInitializer start");
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("/news.sql"));

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(db2DataSource());

        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        LOGGER.debug("DbConfig.dataSourceInitializer end");
        return dataSourceInitializer;
    }


}
