package com.solvd.online.store.impl;
import com.solvd.online.store.model.ShippingMethod;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.solvd.online.store.dao.IShippingMethodDAO;
import com.solvd.online.store.util.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;

public class ShippingMethodDAO implements IShippingMethodDAO {
    private final static Logger LOGGER = LogManager.getLogger(ShippingMethodDAO.class);
    private static final String INSERT = "INSERT INTO ShippingMethod (shippingId, methodName, shippingCost, shippingTime) VALUES (?,?,?,?)";
    private static final String UPDATE = "UPDATE ShippingMethod SET methodName=?, shippingCost=?, shippingTime=? WHERE shippingId=?";
    private static final String DELETE = "DELETE FROM ShippingMethod WHERE shippingId=?";
    private static final String GET = "SELECT * FROM ShippingMethod WHERE shippingId=?";

    @Override
    public void insert(ShippingMethod shippingMethod) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        if(shippingMethod == null){
            LOGGER.error("ShippingMethod Object is null.");
            throw new NullPointerException();
        }
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setInt(1, shippingMethod.getShippingId());
            preparedStatement.setString(2, shippingMethod.getMethodName());
            preparedStatement.setDouble(3, shippingMethod.getShippingCost());
            preparedStatement.setString(4, shippingMethod.getShippingTime());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.");
            throw new RuntimeException(e);
        } finally {
            closeResources(connectionPool, connection, preparedStatement);
        }
    }

    @Override
    public void update(ShippingMethod shippingMethod) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        if(shippingMethod == null){
            LOGGER.error("ShippingMethod Object is null.");
            throw new NullPointerException();
        }
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, shippingMethod.getMethodName());
            preparedStatement.setDouble(2, shippingMethod.getShippingCost());
            preparedStatement.setString(3, shippingMethod.getShippingTime());
            preparedStatement.setInt(4, shippingMethod.getShippingId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.");
            throw new RuntimeException(e);
        } finally {
            closeResources(connectionPool, connection, preparedStatement);
        }
    }

    @Override
    public void deleteById(int id) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.");
            throw new RuntimeException(e);
        } finally {
            closeResources(connectionPool, connection, preparedStatement);
        }
    }

    @Override
    public ShippingMethod getById(int id) {
        ShippingMethod shippingMethod = new ShippingMethod();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(GET);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                shippingMethod.setShippingId(resultSet.getInt("shippingId"));
                shippingMethod.setMethodName(resultSet.getString("methodName"));
                shippingMethod.setShippingCost(resultSet.getDouble("shippingCost"));
                shippingMethod.setShippingTime(resultSet.getString("shippingTime"));
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
            if (connection != null) {
                connectionPool.releaseConnection(connection);
            }
        }
        return shippingMethod;
    }

    private void closeResources(ConnectionPool connectionPool, Connection connection, PreparedStatement preparedStatement){
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to close Prepared Statement.");
            throw new RuntimeException(e);
        }
        if (connection != null) {
            connectionPool.releaseConnection(connection);
        }
    }
}
