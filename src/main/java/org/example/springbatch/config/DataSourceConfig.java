package org.example.springbatch.config;

import org.springframework.boot.batch.jdbc.autoconfigure.BatchDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    @Primary
    public DataSource appDataSource(){
        return DataSourceBuilder.create().build();
    }

    @ConfigurationProperties(prefix = "spring.batch.datasource")
    @Bean
    @BatchDataSource
    public DataSource batchDataSource(){
        return DataSourceBuilder.create().build();
    }
}
