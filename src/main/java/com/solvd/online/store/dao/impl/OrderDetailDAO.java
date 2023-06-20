package com.solvd.online.store.dao.impl;
import com.solvd.online.store.model.OrderDetail;
import com.solvd.online.store.dao.IOrderDetailDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.online.store.util.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDetailDAO implements IOrderDetailDAO {
    private final static Logger LOGGER = LogManager.getLogger(OrderDetailDAO.class);
    private static final String INSERT_QUERY = "INSERT INTO OrderDetail (orderId, productId, shippingId, quantity) VALUES (?,?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE OrderDetail SET productId=?, shippingId=?, quantity=? WHERE orderId=?";
    private static final String DELETE_QUERY = "DELETE FROM OrderDetail WHERE orderId=?";
    private static final String GET_QUERY = "SELECT * FROM OrderDetail WHERE orderId=?";

    @Override
    public void insert(OrderDetail orderDetail) {
        if (orderDetail == null) {
            LOGGER.error("OrderDetail object is null.");
            throw new IllegalArgumentException("OrderDetail object is null.");
        }

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {

            preparedStatement.setInt(1, orderDetail.getOrderId());
            preparedStatement.setInt(2, orderDetail.getProductId());
            preparedStatement.setInt(3, orderDetail.getShippingId());
            preparedStatement.setInt(4, orderDetail.getQuantity());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Unable to execute prepared statement.", e);
            throw new RuntimeException("Unable to execute prepared statement.", e);
        }
    }

    @Override
    public void update(OrderDetail orderDetail) {
        if (orderDetail == null) {
            LOGGER.error("OrderDetail object is null.");
            throw new IllegalArgumentException("OrderDetail object is null.");
        }

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {

            preparedStatement.setInt(1, orderDetail.getProductId());
            preparedStatement.setInt(2, orderDetail.getShippingId());
            preparedStatement.setInt(3, orderDetail.getQuantity());
            preparedStatement.setInt(4, orderDetail.getOrderId());
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
    public OrderDetail getById(int id) {
        OrderDetail orderDetail = new OrderDetail();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_QUERY)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    orderDetail.setOrderId(resultSet.getInt("orderId"));
                    orderDetail.setProductId(resultSet.getInt("productId"));
                    orderDetail.setShippingId(resultSet.getInt("shippingId"));
                    orderDetail.setQuantity(resultSet.getInt("quantity"));
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Unable to obtain resource.", e);
            throw new RuntimeException("Unable to obtain resource.", e);
        }
        return orderDetail;
    }
}