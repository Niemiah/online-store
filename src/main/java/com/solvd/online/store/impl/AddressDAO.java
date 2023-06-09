package com.solvd.online.store.impl;
import com.solvd.online.store.dao.IAddressDAO;
import com.solvd.online.store.model.Address;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.online.store.util.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressDAO implements IAddressDAO {
    private final static Logger LOGGER = LogManager.getLogger(AddressDAO.class);
    private static final String INSERT = "INSERT INTO Address (addressId, address, city, state, postalCode, country) VALUES (?,?,?,?,?,?)";
    private static final String UPDATE = "UPDATE Address SET address=?, city=?, state=?, postalCode=?, country=? WHERE addressId=?";
    private static final String DELETE = "DELETE FROM Address WHERE addressId=?";
    private static final String GET = "SELECT * FROM Address WHERE addressId=?";

    @Override
    public void insert(Address address) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            LOGGER.error("Unable to get connection from pool.", e);
            throw new RuntimeException(e);
        }
        if(address == null){
            LOGGER.error("Address Object is null.");
            throw new NullPointerException();
        }
        try {
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setInt(1, address.getAddressId());
            preparedStatement.setString(2, address.getAddress());
            preparedStatement.setString(3, address.getCity());
            preparedStatement.setString(4, address.getState());
            preparedStatement.setString(5, address.getPostalCode());
            preparedStatement.setString(6, address.getCountry());
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
    public void update(Address address) {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
        } catch (SQLException e) {
            LOGGER.error("Unable to get connection from pool.", e);
            throw new RuntimeException(e);
        }
        if(address == null){
            LOGGER.error("Address Object is null.");
            throw new NullPointerException();
        }
        try {
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, address.getAddress());
            preparedStatement.setString(2, address.getCity());
            preparedStatement.setString(3, address.getState());
            preparedStatement.setString(4, address.getPostalCode());
            preparedStatement.setString(5, address.getCountry());
            preparedStatement.setInt(6, address.getAddressId());
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
    public Address getById(int id) {
        Address address = new Address();
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
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                address.setAddressId(resultSet.getInt("addressId"));
                address.setAddress(resultSet.getString("address"));
                address.setCity(resultSet.getString("city"));
                address.setState(resultSet.getString("state"));
                address.setPostalCode(resultSet.getString("postalCode"));
                address.setCountry(resultSet.getString("country"));
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
        return address;
    }
}