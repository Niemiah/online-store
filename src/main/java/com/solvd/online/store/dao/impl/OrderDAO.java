package com.solvd.online.store.dao.impl;
import com.solvd.online.store.model.Order;
import com.solvd.online.store.dao.IOrderDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.online.store.util.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class OrderDAO implements IOrderDAO {
    private final static Logger LOGGER = LogManager.getLogger(OrderDAO.class);
    private static final String INSERT_QUERY = "INSERT INTO `Order` (orderId, userId) VALUES (?,?)";
    private static final String UPDATE_QUERY = "UPDATE `Order` SET userId=? WHERE orderId=?";
    private static final String DELETE_QUERY = "DELETE FROM `Order` WHERE orderId=?";
    private static final String GET_QUERY = "SELECT * FROM `Order` WHERE orderId=?";

    @Override
    public void insert(Order order) {
        if (order == null) {
            LOGGER.error("Order object is null.");
            throw new IllegalArgumentException("Order object is null.");
        }

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {

            preparedStatement.setInt(1, order.getOrderId());
            preparedStatement.setInt(2, order.getUserId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Unable to execute prepared statement.", e);
            throw new RuntimeException("Unable to execute prepared statement.", e);
        }
    }

    @Override
    public void update(Order order) {
        if (order == null) {
            LOGGER.error("Order object is null.");
            throw new IllegalArgumentException("Order object is null.");
        }

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {

            preparedStatement.setInt(1, order.getUserId());
            preparedStatement.setInt(2, order.getOrderId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Unable to execute prepared statement.", e);
            throw new RuntimeException("Unable to execute prepared statement.", e);
        }
    }

    @Override
    public void deleteById(int id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Unable to execute prepared statement.", e);
            throw new RuntimeException("Unable to execute prepared statement.", e);
        }
    }

    @Override
    public Order getById(int id) {
        Order order = new Order();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_QUERY)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    order.setOrderId(resultSet.getInt("orderId"));
                    order.setUserId(resultSet.getInt("userId"));
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Unable to obtain resource.", e);
            throw new RuntimeException("Unable to obtain resource.", e);
        }
        return order;
    }
}