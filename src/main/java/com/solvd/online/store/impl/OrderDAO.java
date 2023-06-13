package com.solvd.online.store.impl;
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
    private static final String INSERT = "INSERT INTO Order (orderId, userId) VALUES (?,?)";
    private static final String UPDATE = "UPDATE Order SET userId=? WHERE orderId=?";
    private static final String DELETE = "DELETE FROM Order WHERE orderId=?";
    private static final String GET = "SELECT * FROM Order WHERE orderId=?";

    @Override
    public void insert(Order order) {
        if(order == null){
            LOGGER.error("Order Object is null.");
            throw new NullPointerException();
        }
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {

            preparedStatement.setInt(1, order.getOrderId());
            preparedStatement.setInt(2, order.getUserId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Order order) {
        if(order == null){
            LOGGER.error("Order Object is null.");
            throw new NullPointerException();
        }
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {

            preparedStatement.setInt(1, order.getUserId());
            preparedStatement.setInt(2, order.getOrderId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(int id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Order getById(int id) {
        Order order = new Order();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()){
                    order.setOrderId(resultSet.getInt("orderId"));
                    order.setUserId(resultSet.getInt("userId"));
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Unable to obtain resource.", e);
            throw new RuntimeException(e);
        }
        return order;
    }
}