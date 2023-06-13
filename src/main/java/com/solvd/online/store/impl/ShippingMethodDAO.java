package com.solvd.online.store.impl;
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
    private static final String INSERT = "INSERT INTO ShippingMethod (shippingId, methodName, shippingCost, shippingTime) VALUES (?,?,?,?)";
    private static final String UPDATE = "UPDATE ShippingMethod SET methodName=?, shippingCost=?, shippingTime=? WHERE shippingId=?";
    private static final String DELETE = "DELETE FROM ShippingMethod WHERE shippingId=?";
    private static final String GET = "SELECT * FROM ShippingMethod WHERE shippingId=?";

    @Override
    public void insert(ShippingMethod shippingMethod) {
        if(shippingMethod == null){
            LOGGER.error("ShippingMethod Object is null.");
            throw new NullPointerException();
        }
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {

            preparedStatement.setInt(1, shippingMethod.getShippingId());
            preparedStatement.setString(2, shippingMethod.getMethodName());
            preparedStatement.setDouble(3, shippingMethod.getShippingCost());
            preparedStatement.setString(4, shippingMethod.getShippingTime());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(ShippingMethod shippingMethod) {
        if(shippingMethod == null){
            LOGGER.error("ShippingMethod Object is null.");
            throw new NullPointerException();
        }
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {

            preparedStatement.setString(1, shippingMethod.getMethodName());
            preparedStatement.setDouble(2, shippingMethod.getShippingCost());
            preparedStatement.setString(3, shippingMethod.getShippingTime());
            preparedStatement.setInt(4, shippingMethod.getShippingId());
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
    public ShippingMethod getById(int id) {
        ShippingMethod shippingMethod = new ShippingMethod();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()){
                    shippingMethod.setShippingId(resultSet.getInt("shippingId"));
                    shippingMethod.setMethodName(resultSet.getString("methodName"));
                    shippingMethod.setShippingCost(resultSet.getDouble("shippingCost"));
                    shippingMethod.setShippingTime(resultSet.getString("shippingTime"));
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Unable to obtain resource.", e);
            throw new RuntimeException(e);
        }
        return shippingMethod;
    }
}