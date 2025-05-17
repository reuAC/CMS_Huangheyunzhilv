package org.reuac.yrt_001.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSourceConfig {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);
    private static final HikariDataSource dataSource;

    static {
        try {
            Properties props = new Properties();
            InputStream inputStream = DataSourceConfig.class.getClassLoader().getResourceAsStream("db.properties");
            if (inputStream == null) {
                logger.error("Sorry, unable to find db.properties");
                throw new RuntimeException("db.properties not found in classpath");
            }
            props.load(inputStream);

            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(props.getProperty("db.url"));
            config.setUsername(props.getProperty("db.username"));
            config.setPassword(props.getProperty("db.password"));
            config.setDriverClassName(props.getProperty("db.driver"));

            config.setPoolName(props.getProperty("hikari.poolName", "HikariPool-Default"));
            config.setMaximumPoolSize(Integer.parseInt(props.getProperty("hikari.maximumPoolSize", "10")));
            config.setMinimumIdle(Integer.parseInt(props.getProperty("hikari.minimumIdle", "2")));
            config.setConnectionTimeout(Long.parseLong(props.getProperty("hikari.connectionTimeout", "30000")));
            config.setIdleTimeout(Long.parseLong(props.getProperty("hikari.idleTimeout", "600000")));
            config.setMaxLifetime(Long.parseLong(props.getProperty("hikari.maxLifetime", "1800000")));

            dataSource = new HikariDataSource(config);
            logger.info("HikariCP DataSource initialized successfully.");

        } catch (IOException e) {
            logger.error("Error loading db.properties", e);
            throw new RuntimeException("Error loading db.properties", e);
        } catch (Exception e) {
            logger.error("Error initializing HikariCP DataSource", e);
            throw new RuntimeException("Error initializing HikariCP DataSource", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            logger.error("DataSource is not initialized!");
            throw new SQLException("DataSource is not initialized.");
        }
        return dataSource.getConnection();
    }

    public static void closeDataSource() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
            logger.info("HikariCP DataSource closed.");
        }
    }
}