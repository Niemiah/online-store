package com.solvd.db.impl;
import com.solvd.online.store.locations.Address;
import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.solvd.online.store.ConnectionPool.getConnection;

public class AddressDaoImpl extends JdbcDao<Address, Integer> {
    public AddressDaoImpl(BasicDataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected Address mapRowToEntity(ResultSet resultSet) throws SQLException {
        Address address = new Address();
        address.setAddressId(resultSet.getInt("address_id"));
        address.setAddressLine1(resultSet.getString("address_line1"));
        address.setCity(resultSet.getString("city"));
        address.setState(resultSet.getString("state"));
        address.setPostalCode(resultSet.getString("postal_code"));
        address.setCountry(resultSet.getString("country"));
        return address;
    }

    @Override
    protected String getTableName() {
        return "Addresses";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO Addresses (address_line1, city, state, postal_code, country) VALUES (?, ?, ?, ?, ?)";
    }

    @Override
    protected String getFindQuery() {
        return "SELECT * FROM Addresses WHERE address_id = ?";
    }

    @Override
    protected String getFindAllQuery() {
        return "SELECT * FROM Addresses";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE Addresses SET address_line1 = ?, city = ?, state = ?, postal_code = ?, country = ? WHERE address_id = ?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM Addresses WHERE address_id = ?";
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, Address address) throws SQLException {
        statement.setString(1, address.getAddressLine1());
        statement.setString(2, address.getCity());
        statement.setString(3, address.getState());
        statement.setString(4, address.getPostalCode());
        statement.setString(5, address.getCountry());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Address address) throws SQLException {
        statement.setString(1, address.getAddressLine1());
        statement.setString(2, address.getCity());
        statement.setString(3, address.getState());
        statement.setString(4, address.getPostalCode());
        statement.setString(5, address.getCountry());
        statement.setInt(6, address.getAddressId());
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, Address address) throws SQLException {
        statement.setInt(1, address.getAddressId());
    }

    @Override
    public Address create(Address address) {
        try (PreparedStatement statement = getConnection().prepareStatement(getCreateQuery())) {
            prepareStatementForCreate(statement, address);
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating address failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    address.setAddressId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating address failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return address;
    }

    @Override
    public Address find(int id) {
        return null;
    }

    @Override
    public List<Address> findAll() {
        List<Address> addresses = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(getFindAllQuery());
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                addresses.add(mapRowToEntity(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return addresses;
    }

    @Override
    public Address update(Address address) {
        try (PreparedStatement statement = getConnection().prepareStatement(getUpdateQuery())) {
            prepareStatementForUpdate(statement, address);
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating address failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return address;
    }

    @Override
    public void delete(Address address) {
        try (PreparedStatement statement = getConnection().prepareStatement(getDeleteQuery())) {
            prepareStatementForDelete(statement, address);
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Deleting address failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}