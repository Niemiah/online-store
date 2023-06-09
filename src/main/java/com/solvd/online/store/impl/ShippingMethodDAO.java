package com.solvd.online.store.impl;
import com.solvd.online.store.model.User;
import com.solvd.online.store.model.ShippingMethod;
import com.solvd.online.store.model.OrderDetail;
import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.solvd.online.store.util.ConnectionPool.getConnection;

public class ShippingMethodDAO extends JdbcDao<ShippingMethod, Integer> {
    public ShippingMethodDAO(BasicDataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected ShippingMethod mapRowToEntity(ResultSet resultSet) throws SQLException {
        ShippingMethod shippingMethod = new ShippingMethod();
        shippingMethod.setShippingId(resultSet.getInt("shipping_id"));
        shippingMethod.setMethodName(resultSet.getString("method_name"));
        shippingMethod.setShippingCost(resultSet.getDouble("shipping_cost"));
        shippingMethod.setShippingTime(resultSet.getString("shipping_time"));
        return shippingMethod;
    }

    @Override
    protected String getTableName() {
        return "Shipping_Methods";
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO Shipping_Methods (method_name, shipping_cost, shipping_time) VALUES (?, ?, ?)";
    }

    @Override
    protected String getFindQuery() {
        return "SELECT * FROM Shipping_Methods WHERE shipping_id = ?";
    }

    @Override
    protected String getFindAllQuery() {
        return "SELECT * FROM Shipping_Methods";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE Shipping_Methods SET method_name = ?, shipping_cost = ?, shipping_time = ? WHERE shipping_id = ?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM Shipping_Methods WHERE shipping_id = ?";
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement statement, ShippingMethod shippingMethod) throws SQLException {
        statement.setString(1, shippingMethod.getMethodName());
        statement.setDouble(2, shippingMethod.getShippingCost());
        statement.setString(3, shippingMethod.getShippingTime());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, ShippingMethod shippingMethod) throws SQLException {
        statement.setString(1, shippingMethod.getMethodName());
        statement.setDouble(2, shippingMethod.getShippingCost());
        statement.setString(3, shippingMethod.getShippingTime());
        statement.setInt(4, shippingMethod.getShippingId());
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, ShippingMethod shippingMethod) throws SQLException {
        statement.setInt(1, shippingMethod.getShippingId());
    }

    @Override
    public OrderDetail find(Integer orderId, Integer productId) {
        return null;
    }

    @Override
    public ShippingMethod create(ShippingMethod shippingMethod) {
        try (PreparedStatement statement = getConnection().prepareStatement(getCreateQuery())) {
            prepareStatementForCreate(statement, shippingMethod);
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating shipping method failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    shippingMethod.setShippingId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating shipping method failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shippingMethod;
    }

    @Override
    public ShippingMethod find(int id) {
        return null;
    }

    @Override
    public User find(Integer shippingId) {
        ShippingMethod shippingMethod = null;
        try (PreparedStatement statement = getConnection().prepareStatement(getFindQuery())) {
            statement.setInt(1, shippingId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    shippingMethod = mapRowToEntity(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shippingMethod;
    }

    @Override
    public List<ShippingMethod> findAll() {
        List<ShippingMethod> shippingMethods = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(getFindAllQuery());
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                shippingMethods.add(mapRowToEntity(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shippingMethods;
    }

    @Override
    public ShippingMethod update(ShippingMethod shippingMethod) {
        try (PreparedStatement statement = getConnection().prepareStatement(getUpdateQuery())) {
            prepareStatementForUpdate(statement, shippingMethod);
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating shipping method failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shippingMethod;
    }

    @Override
    public void delete(ShippingMethod shippingMethod) {
        try (PreparedStatement statement = getConnection().prepareStatement(getDeleteQuery())) {
            prepareStatementForDelete(statement, shippingMethod);
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Deleting shipping method failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
