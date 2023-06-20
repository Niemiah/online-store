package com.solvd.online.store.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static final String PROPERTIES_FILE = "src/main/resources/db.properties";
    private static final int MAX_CONNECTIONS = 10;
    private final BlockingQueue<Connection> connectionPool = new ArrayBlockingQueue<>(MAX_CONNECTIONS);

    // Singleton Instance
    private static volatile ConnectionPool instance;

    private ConnectionPool() {
        for (int i = 0; i < MAX_CONNECTIONS; i++) {
            Connection connection = createConnection();
            if (connection != null) {
                connectionPool.offer(connection);
            }
        }
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool();
                }
            }
        }
        return instance;
    }

    public synchronized Connection getConnection() {
        Connection connection = null;
        try {
            connection = connectionPool.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Set the interrupt flag
            LOGGER.error("Failed to take connection from pool.", e);
        }
        return connection;
    }

    public synchronized void releaseConnection(Connection connection) {
        if (connection != null) {
            connectionPool.offer(connection);
        }
    }

    private Connection createConnection() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(PROPERTIES_FILE)) {
            properties.load(input);
            return DriverManager.getConnection(properties.getProperty("db.url"),
                    properties.getProperty("db.username"), properties.getProperty("db.password"));
        } catch (IOException | SQLException e) {
            LOGGER.error("Failed to create database connection.", e);
        }
        return null;
    }
}