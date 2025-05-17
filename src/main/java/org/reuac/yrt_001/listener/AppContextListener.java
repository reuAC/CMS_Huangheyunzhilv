package org.reuac.yrt_001.listener;

import org.reuac.yrt_001.config.DataSourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {
    private static final Logger logger = LoggerFactory.getLogger(AppContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("Application Context Initialized. Initializing DataSource...");

        try {
            Class.forName("org.reuac.yrt_001.config.DataSourceConfig");
        } catch (ClassNotFoundException e) {
            logger.error("Failed to explicitly load DataSourceConfig class.", e);
        }
        logger.info("DataSource should be initialized by now.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("Application Context Destroyed. Closing DataSource...");
        DataSourceConfig.closeDataSource();
        logger.info("DataSource closed.");


    }
}