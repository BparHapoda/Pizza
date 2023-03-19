package com.example.pizza.listener;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.io.IOException;
import java.util.Properties;

@WebListener
public class dataSourceInit implements ServletContextListener {

    HikariDataSource dataSource;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext= sce.getServletContext();
        Properties properties=new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HikariConfig hikariConfig=new HikariConfig();
        hikariConfig.setUsername(properties.getProperty("db.user"));
        hikariConfig.setJdbcUrl(properties.getProperty("db.url"));
        hikariConfig.setPassword(properties.getProperty("db.password"));
        hikariConfig.setMaximumPoolSize(Integer.parseInt(properties.getProperty("db.pool")));
        hikariConfig.setDriverClassName(properties.getProperty("db.driver"));

        this.dataSource=new HikariDataSource(hikariConfig);
        servletContext.setAttribute("db",dataSource);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        dataSource.close();
    }
}
