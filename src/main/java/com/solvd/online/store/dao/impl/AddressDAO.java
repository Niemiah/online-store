package com.solvd.online.store.dao.impl;
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
        if(address == null){
            LOGGER.error("Address Object is null.");
            throw new NullPointerException();
        }
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {

            preparedStatement.setInt(1, address.getAddressId());
            preparedStatement.setString(2, address.getAddress());
            preparedStatement.setString(3, address.getCity());
            preparedStatement.setString(4, address.getState());
            preparedStatement.setString(5, address.getPostalCode());
            preparedStatement.setString(6, address.getCountry());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Unable to execute Prepared Statement.", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Address address) {
        if(address == null){
            LOGGER.error("Address Object is null.");
            throw new NullPointerException();
        }
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {

            preparedStatement.setString(1, address.getAddress());
            preparedStatement.setString(2, address.getCity());
            preparedStatement.setString(3, address.getState());
            preparedStatement.setString(4, address.getPostalCode());
            preparedStatement.setString(5, address.getCountry());
            preparedStatement.setInt(6, address.getAddressId());
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
    public Address getById(int id) {
        Address address = new Address();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET)) {

            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while(resultSet.next()){
                    address.setAddressId(resultSet.getInt("addressId"));
                    address.setAddress(resultSet.getString("address"));
                    address.setCity(resultSet.getString("city"));
                    address.setState(resultSet.getString("state"));
                    address.setPostalCode(resultSet.getString("postalCode"));
                    address.setCountry(resultSet.getString("country"));
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Unable to obtain resource.", e);
            throw new RuntimeException(e);
        }
        return address;
    }
}