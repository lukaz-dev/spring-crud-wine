package br.com.wine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Profile("dev")
@Configuration
public class DataSourceDevConfig {

    @Bean
    public DataSource dataSource(Environment environment){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost/winedb?useSSL=false&createDatabaseIfNotExist=true");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }
}
