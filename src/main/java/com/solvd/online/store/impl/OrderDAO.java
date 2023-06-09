package com.solvd.online.store.impl;
import com.solvd.online.store.model.Order;
import com.solvd.online.store.util.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.solvd.online.store.dao.IOrderDAO;

public class OrderDAO implements IOrderDAO {
    private final static Logger LOGGER = LogManager.getLogger(OrderDAO.class);
    private static final String INSERT = "INSERT INTO Order (orderId, userId) VALUES (?,?)";
    private static final String UPDATE = "UPDATE Order SET userId=? WHERE orderId=?";
    private static final String DELETE = "DELETE FROM Order WHERE orderId=?";
    private static final String GET = "SELECT * FROM Order WHERE orderId=?";

    @Override
    public void insert(Order order) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            LOGGER.error("Unable to get connection from pool.", e);
            throw new RuntimeException(e);
        }
        if(order == null){
            LOGGER.error("Order Object is null.");
            throw new NullPointerException();
        }
        try {
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setInt(1, order.getOrderId());
            preparedStatement.setInt(2, order.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.");
            throw new RuntimeException(e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                LOGGER.error("Unable to close Prepared Statement.");
                throw new RuntimeException(e);
            }
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void update(Order order) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            LOGGER.error("Unable to get connection from pool.", e);
            throw new RuntimeException(e);
        }
        if(order == null){
            LOGGER.error("Order Object is null.");
            throw new NullPointerException();
        }
        try {
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setInt(1, order.getUserId());
            preparedStatement.setInt(2, order.getOrderId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.");
            throw new RuntimeException(e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                LOGGER.error("Unable to close Prepared Statement.");
                throw new RuntimeException(e);
            }
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(int id) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            LOGGER.error("Unable to get connection from pool.", e);
            throw new RuntimeException(e);
        }
        try {
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.");
            throw new RuntimeException(e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                LOGGER.error("Unable to close Prepared Statement.");
                throw new RuntimeException(e);
            }
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Order getById(int id) {
        Order order = new Order();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            LOGGER.error("Unable to get connection from pool.", e);
            throw new RuntimeException(e);
        }
        try {
            preparedStatement = connection.prepareStatement(GET);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                order.setOrderId(resultSet.getInt("orderId"));
                order.setUserId(resultSet.getInt("userId"));
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to obtain resource.");
            throw new RuntimeException(e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.error("Unable to close resource.");
                throw new RuntimeException(e);
            }
            connectionPool.releaseConnection(connection);
        }
        return order;
    }
}