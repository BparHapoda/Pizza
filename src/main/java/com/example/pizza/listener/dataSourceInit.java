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
        ServletContext context = sce.getServletContext();
        Properties properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(properties.getProperty("db.url"));
        config.setPassword(properties.getProperty("db.password"));
        config.setDriverClassName(properties.getProperty("db.driver"));
        config.setMaximumPoolSize(Integer.parseInt(properties.getProperty("db.pool")));
        this.dataSource = new HikariDataSource(config);
        context.setAttribute("db", dataSource);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        dataSource.close();
    }
}
