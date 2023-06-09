package com.solvd.online.store.util;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static final int MAX_CONNECTIONS = 15;
    private static ConnectionPool connectionPool;
    private final BasicDataSource ds;
    private final List<Connection> connections;

    private ConnectionPool(){
        ds = new BasicDataSource();
        ds.setUrl("jdbc:postgresql://localhost:5432/online_store"); // replace with your db url
        ds.setUsername("db_user");
        ds.setPassword("db_password");
        ds.setMaxIdle(5);
        ds.setMaxOpenPreparedStatements(100);
        connections = Collections.synchronizedList(new LinkedList<>());
    }

    public static ConnectionPool getInstance(){
        if(connectionPool == null){
            connectionPool = new ConnectionPool();
        }
        return connectionPool;
    }

    public int getNumConnections() {
        return connections.size();
    }

    public Connection getConnection() throws SQLException {
        synchronized (connections) {
            if(!connections.isEmpty()) {
                LOGGER.debug("Reusing an existing connection.");
                return connections.remove(0);
            } else if(connections.size() < MAX_CONNECTIONS) {
                LOGGER.debug("Establishing new connection.");
                Connection connection = ds.getConnection();
                connections.add(connection);
                return connection;
            } else {
                LOGGER.debug("Max connections reached. Please wait.");
                while(connections.isEmpty()) {
                    try {
                        connections.wait();
                    } catch(InterruptedException e) {
                        LOGGER.error("Thread interrupted while waiting for connection", e);
                    }
                }
                return connections.remove(0);
            }
        }
    }

    public void releaseConnection(Connection connection){
        synchronized (connections) {
            try {
                if (connection != null && !connection.isClosed()) {
                    connections.add(connection);
                    connections.notify();
                    LOGGER.debug("Connection successfully released.");
                }
            } catch (SQLException e) {
                LOGGER.error("Failed to close connection", e);
            }
        }
    }
}