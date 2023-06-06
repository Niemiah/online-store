package com.solvd.online.store;
import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/online-store";
    private static final String DB_USER = "username";
    private static final String DB_PASSWORD = "password";

    private static BasicDataSource dataSource;

    static {
        dataSource = new BasicDataSource();
        dataSource.setUrl(DB_URL);
        dataSource.setUsername(DB_USER);
        dataSource.setPassword(DB_PASSWORD);

        // Set other optional parameters
        dataSource.setInitialSize(5);
        dataSource.setMaxTotal(10);
        dataSource.setMaxIdle(5);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void main(String[] args) {
        try (Connection connection = getConnection()) {
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}