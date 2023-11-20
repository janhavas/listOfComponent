package com.whirlpool.component_list.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application.properties")
public class DataSourceConfig {

    //first database
    @Primary
    @Bean(name = "mii")
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties firstDataSourceProperties() {
        return new DataSourceProperties();
    }


    @Primary
    @Bean(name = "miiDb")
    public HikariDataSource firstDataSource(@Qualifier("mii") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean("miiJdbc")
    public JdbcTemplate jdbcTemplate1(@Qualifier("miiDb") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }




    //second database

    @Bean(name = "cell")
    @ConfigurationProperties("app.datasource.second")
    public DataSourceProperties secondDataSourceProperties() {
        return new DataSourceProperties();
    }


    @Bean(name = "cellDb")
    public HikariDataSource secondDataSource(@Qualifier("cell") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean("cellJdbc")
    public JdbcTemplate jdbcTemplate2(@Qualifier("cellDb") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
