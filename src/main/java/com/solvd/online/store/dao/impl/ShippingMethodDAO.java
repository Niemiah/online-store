package com.solvd.online.store.dao.impl;
import com.solvd.online.store.dao.IShippingMethodDAO;
import com.solvd.online.store.model.ShippingMethod;
import com.solvd.online.store.util.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShippingMethodDAO implements IShippingMethodDAO {
    private final static Logger LOGGER = LogManager.getLogger(ShippingMethodDAO.class);
    private static final String INSERT_QUERY = "INSERT INTO ShippingMethod (shippingId, methodName, shippingCost, shippingTime) VALUES (?,?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE ShippingMethod SET methodName=?, shippingCost=?, shippingTime=? WHERE shippingId=?";
    private static final String DELETE_QUERY = "DELETE FROM ShippingMethod WHERE shippingId=?";
    private static final String GET_QUERY = "SELECT * FROM ShippingMethod WHERE shippingId=?";

    @Override
    public void insert(ShippingMethod shippingMethod) {
        if (shippingMethod == null) {
            LOGGER.error("ShippingMethod object is null.");
            throw new IllegalArgumentException("ShippingMethod object is null.");
        }

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {

            preparedStatement.setInt(1, shippingMethod.getShippingId());
            preparedStatement.setString(2, shippingMethod.getMethodName());
            preparedStatement.setDouble(3, shippingMethod.getShippingCost());
            preparedStatement.setString(4, shippingMethod.getShippingTime());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Unable to execute prepared statement.", e);
            throw new RuntimeException("Unable to execute prepared statement.", e);
        }
    }

    @Override
    public void update(ShippingMethod shippingMethod) {
        if (shippingMethod == null) {
            LOGGER.error("ShippingMethod object is null.");
            throw new IllegalArgumentException("ShippingMethod object is null.");
        }

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {

            preparedStatement.setString(1, shippingMethod.getMethodName());
            preparedStatement.setDouble(2, shippingMethod.getShippingCost());
            preparedStatement.setString(3, shippingMethod.getShippingTime());
            preparedStatement.setInt(4, shippingMethod.getShippingId());
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
    public ShippingMethod getById(int id) {
        ShippingMethod shippingMethod = new ShippingMethod();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_QUERY)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    shippingMethod.setShippingId(resultSet.getInt("shippingId"));
                    shippingMethod.setMethodName(resultSet.getString("methodName"));
                    shippingMethod.setShippingCost(resultSet.getDouble("shippingCost"));
                    shippingMethod.setShippingTime(resultSet.getString("shippingTime"));
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Unable to obtain resource.", e);
            throw new RuntimeException("Unable to obtain resource.", e);
        }
        return shippingMethod;
    }
}